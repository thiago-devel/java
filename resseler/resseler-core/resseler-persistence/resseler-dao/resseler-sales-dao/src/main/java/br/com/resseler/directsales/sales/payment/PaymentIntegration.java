package br.com.resseler.directsales.sales.payment;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_AMEX;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_AURA;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_DINERS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_ELO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_HIPER;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_MASTER;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_PADRAO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BANDEIRA_CARTAO_VISA;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ESTADO_CIVIL_CASADO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ESTADO_CIVIL_SOLTEIRO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.FLAG_SEXO_FEMININO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.FLAG_SEXO_MASCULINO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.MASTERPOLICE_PADRAO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.MASTERPOLICE_PROTECAOPREMIADA;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_PADRAO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_CARTAO_SUPERPROTEGIDO;
import static br.com.resseler.directsales.sales.payment.PaymentIntegrationBilling.mountBillingPaymentType;
import static br.com.resseler.directsales.sales.payment.PaymentIntegrationBilling.processCreditCardPaymentType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.validation.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.resseler.directsales.sales.persistence.builders.BillingPaymentType;
import br.com.resseler.directsales.sales.persistence.builders.BillingPaymentTypeBuilder;
import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.PhoneDTO;
import br.com.rubyit.resseler.commons.kernel.enums.Gender;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

public final class PaymentIntegration {

    private static final String CODE_CREDIT_CARD_LUIZA_MASTER = "41";
    private static final String CODE_CREDIT_CARD_VISA = "40";
    private static final String CODE_CREDIT_CARD_AMEX = "42";
    private static final String CODE_CREDIT_CARD_DINNERS = "44";
    private static final String CODE_CREDIT_CARD_AURA = "43";
    private static final String CODE_CREDIT_CARD_HIPER = "45";
    private static final String CODE_CREDIT_CARD_ELO = "61";
    private static final Logger LOG = LogManager.getLogger(PaymentIntegration.class);

    private PaymentIntegration() {
    }

    public static void processCreditCardPayment(final Sale sale, final String contractNumber,
            final BigDecimal productValue, final BigInteger numeroParcelas) {

        final /*PaymentType*/Object paymentType = preparePaymentType(sale, contractNumber, productValue, numeroParcelas);

        processCreditCardPaymentType(paymentType);
    }

    private static /*PaymentType*/Object preparePaymentType(final Sale sale, final String contractNumber,
            final BigDecimal productValue, final BigInteger numeroParcelas) {
        if (sale == null) {
            final String msg = "ERROR: invalid Sale data! Sale=[" + sale + "]";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        final PaymentMethod paymentMethod = sale.getPaymentMethod();
        if (paymentMethod == null) {
            final String msg = "ERROR: invalid PaymentMethod data! PaymentMethod=[" + paymentMethod + "]";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        final Salesman salesman = sale.getSalesman();
        if ((salesman == null) || (salesman.getPartner() == null)) {
            final String msg = "ERROR: invalid Salesman data! Salesman=[" + salesman + "]";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        validateSaleContent(contractNumber, productValue, numeroParcelas, salesman);
        validadeSaleCustomer(sale);
        validateSaleCreditCard(paymentMethod);
        final /*PaymentType*/Object paymentType = preparePaymentType(sale, contractNumber, productValue, numeroParcelas,
                paymentMethod, salesman);

        return paymentType;
    }

    private static /*PaymentType*/Object preparePaymentType(final Sale sale, final String contractNumber,
            final BigDecimal productValue, final BigInteger numeroParcelas, final PaymentMethod paymentMethod,
            final Salesman salesman) {

        BillingPaymentTypeBuilder builder = new BillingPaymentTypeBuilder();

        builder = fillCreditCardData(numeroParcelas, paymentMethod, builder, sale.getProduct().getID());
        builder = fillExtraData(salesman, builder, sale.getProduct().getID());
        builder = fillPersonalData(sale, contractNumber, productValue, builder);

        final BillingPaymentType billingPaymentType = builder.build();

        return mountBillingPaymentType(billingPaymentType);
    }

    private static BillingPaymentTypeBuilder fillPersonalData(final Sale sale, final String contractNumber,
            final BigDecimal productValue, final BillingPaymentTypeBuilder builder) {
        BillingPaymentTypeBuilder newBuilder = builder;

        final Customer customer = sale.getCustomer();
        final Calendar dataDeNascimentoDoSegurado = customer.getBirthDate();
        final String numeroDeContrato = contractNumber;
        final BigDecimal premio = productValue;
        final String nomeDoSegurado = customer.getFullName();
        final String sexo = fillPersonalGenderData(customer);
        final String email = null;
        String cpfCnpj = null;
        cpfCnpj = fillPersonIdentityData(customer, cpfCnpj);
        final String civilState = fillPersonMaritalData(customer);
        final List<ContactDTO> conts = customer.retrieveContacts();
        for (final ContactDTO contactDTO : conts) {
        	newBuilder = fillPersonalPhoneData(newBuilder, contactDTO);
        	newBuilder = fillPersonalAddressData(newBuilder, contactDTO);
        }

        newBuilder.setDataDeNascimentoDoSegurado(dataDeNascimentoDoSegurado);
        newBuilder.setContractNumber(numeroDeContrato);
        newBuilder.setPremio(premio);
        newBuilder.setNomeDoSegurado(nomeDoSegurado);
        newBuilder.setSexo(sexo);
        newBuilder.setEmail(email);
        newBuilder.setCpfCnpj(cpfCnpj);
        newBuilder.setCivilState(civilState);

        return newBuilder;
    }

    private static BillingPaymentTypeBuilder fillPersonalAddressData(final BillingPaymentTypeBuilder newBuilder,
            final ContactDTO contactDTO) {

        final BillingPaymentTypeBuilder builder = newBuilder;

        String logradouro;
        String numeroEndereco;
        String cidade;
        String CEP;
        String bairro;
        String UF;
        String complemento;
        final AddressDTO address = contactDTO.getAddress();
        if ((address != null) && (address.getType() != null)
                && (address.getType() == br.com.rubyit.resseler.commons.kernel.enums.AddressType.RESIDENTIAL)) {
            logradouro = address.getAddressDetail();
            builder.setLogradouro(logradouro);
            numeroEndereco = String.valueOf(address.getAddressNumber());
            builder.setNumeroEndereco(numeroEndereco);
            bairro = address.getNeighborhood();
            builder.setBairro(bairro);
            cidade = address.getCity();
            builder.setCidade(cidade);
            UF = address.getState();
            builder.setUF(UF);
            CEP = address.getAddressPostalCode();
            builder.setCEP(CEP);
            complemento = address.getAddressReference();
            builder.setComplemento(complemento);
        }

        return builder;
    }

    private static BillingPaymentTypeBuilder fillPersonalPhoneData(final BillingPaymentTypeBuilder newBuilder,
            final ContactDTO contactDTO) {
        final BillingPaymentTypeBuilder builder = newBuilder;

        String dddEtelefoneResidencial;
        String dddEtelefoneCelular;
        String dddETelefoneComercial;
        final PhoneDTO phone = contactDTO.getPhone();
        if ((phone != null) && (phone.getType() != null)
                && (phone.getType() == br.com.rubyit.resseler.commons.kernel.enums.PhoneType.RESIDENTIAL)) {
            dddEtelefoneResidencial = phone.getPhoneNumber();
            builder.setDddEtelefoneResidencial(dddEtelefoneResidencial);
        }
        if ((phone != null) && (phone.getType() != null)
                && (phone.getType() == br.com.rubyit.resseler.commons.kernel.enums.PhoneType.MOBILE)) {
            dddEtelefoneCelular = phone.getPhoneNumber();
            builder.setDddEtelefoneCelular(dddEtelefoneCelular);
        }
        if ((phone != null) && (phone.getType() != null)
                && (phone.getType() == br.com.rubyit.resseler.commons.kernel.enums.PhoneType.BUSINESS)) {
            dddETelefoneComercial = phone.getPhoneNumber();
            builder.setDddETelefoneComercial(dddETelefoneComercial);
        }

        return builder;
    }

    private static String fillPersonMaritalData(final Customer customer) {
        String civilState = null;
        if (customer.getMaritalStatus() != null) {
            if (customer.getMaritalStatus() == MaritalStatus.MARRIED) {
                civilState = ESTADO_CIVIL_CASADO;
            }
            if (customer.getMaritalStatus() == MaritalStatus.SINGLE) {
                civilState = ESTADO_CIVIL_SOLTEIRO;
            }
        }
        return civilState;
    }

    private static String fillPersonIdentityData(final Customer customer, final String oldCpfCnpj) {
        String cpfCnpj = oldCpfCnpj;
        final IdentityDTO identity = customer.getIdentity();
        if ((identity != null) && (identity.getDocumentValue() != null)) {
            cpfCnpj = identity.getDocumentValue();
        }
        return cpfCnpj;
    }

    private static String fillPersonalGenderData(final Customer customer) {
        String sexo = null;
        if (customer.getGender() != null) {
            sexo = (customer.getGender() == Gender.MALE) ? FLAG_SEXO_MASCULINO : FLAG_SEXO_FEMININO;
        }
        return sexo;
    }

    private static BillingPaymentTypeBuilder fillExtraData(final Salesman salesman,
            final BillingPaymentTypeBuilder builder, final Long idProduto) {
        final BillingPaymentTypeBuilder newBuilder = builder;

        String productMasterCode = MASTERPOLICE_PADRAO;
        
        if(!PRODUCT_CARTAO_SUPERPROTEGIDO.equals(idProduto)){
        	productMasterCode = MASTERPOLICE_PROTECAOPREMIADA;
        	newBuilder.setProductMasterCode(productMasterCode);
        }else{
        	newBuilder.setProductMasterCode(productMasterCode);
        }
        

        final String salesmanPartnerCode = (salesman.getPartner() == null) ? PARTNER_PADRAO
                : String.valueOf(salesman.getPartner().getID());
        newBuilder.setSalesmanPartnerCode(salesmanPartnerCode);

        return newBuilder;
    }

    private static BillingPaymentTypeBuilder fillCreditCardData(final BigInteger numeroParcelas,
            final PaymentMethod paymentMethod, final BillingPaymentTypeBuilder builder, final Long idProduto) {

        final BillingPaymentTypeBuilder newBuilder = builder;

        final CardPaymentDTO creditCard = paymentMethod.getCardPayment();
        final String codigoDeSeguranca = creditCard.getCardSecurityCode();
        final BigDecimal valorCobrancaCartao = creditCard.getCardValue();
        final String cartaoNumero = creditCard.getCardNumber();
        final String cartaoNome = creditCard.getCardDisplayName();
        final Calendar cartaoValidade = creditCard.getCardValidity();
        String cartaoCodigoBandeira = null;
        if(creditCard.getCardFlag() != null) {
	        if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_PADRAO)) {
	            cartaoCodigoBandeira = CODE_CREDIT_CARD_LUIZA_MASTER;
	        } else if (!PRODUCT_CARTAO_SUPERPROTEGIDO.equals(idProduto)) {
	            if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_MASTER)) {
	                cartaoCodigoBandeira = CODE_CREDIT_CARD_LUIZA_MASTER;
	            } else if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_VISA)) {
	                cartaoCodigoBandeira = CODE_CREDIT_CARD_VISA;
	            } else if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_HIPER)) {
	                cartaoCodigoBandeira = CODE_CREDIT_CARD_HIPER;
	            } else if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_AMEX)) {
	                cartaoCodigoBandeira = CODE_CREDIT_CARD_AMEX;
	            } else if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_AURA)) {
	                cartaoCodigoBandeira = CODE_CREDIT_CARD_AURA;
	            } else if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_DINERS)) {
	                cartaoCodigoBandeira = CODE_CREDIT_CARD_DINNERS;
	            } else if (creditCard.getCardFlag().trim().equalsIgnoreCase(BANDEIRA_CARTAO_ELO)) {
                        cartaoCodigoBandeira = CODE_CREDIT_CARD_ELO;
                    }
	        }
        }
        newBuilder.setCodigoDeSeguranca(codigoDeSeguranca);
        newBuilder.setValorCobrancaCartao(valorCobrancaCartao);
        newBuilder.setNumeroParcelas(numeroParcelas);
        newBuilder.setCartaoNumero(cartaoNumero);
        newBuilder.setCartaoNome(cartaoNome);
        newBuilder.setCartaoValidade(cartaoValidade);
        newBuilder.setCartaoCodigoBandeira(cartaoCodigoBandeira);

        return newBuilder;
    }

    private static void validateSaleContent(final String contractNumber, final BigDecimal productValue,
            final BigInteger numeroParcelas, final Salesman salesman) {
        if (contractNumber == null) {
            final String msg = "ERROR: invalid contractNumber data! contractNumber=[" + contractNumber + "]";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if (productValue == null) {
            final String msg = "ERROR: invalid productValue data! productValue=[" + salesman + "]";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if (numeroParcelas == null) {
            final String msg = "ERROR: invalid numeroParcelas data! numeroParcelas=[" + salesman + "]";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
    }

    private static void validadeSaleCustomer(final Sale sale) {
        final Customer customer = sale.getCustomer();
        if (customer == null) {
            final String msg = "ERROR: invalid Customer data! Customer is null!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if (customer.getBirthDate() == null) {
            final String msg = "ERROR: invalid Customer data! Customer.birthDate is null!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if (customer.getFullName() == null) {
            final String msg = "ERROR: invalid Customer data! Customer.fullName is null!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        validateSaleCustomerContent(customer);
    }

    private static void validateSaleCustomerContent(final Customer customer) {
        if ((customer.getGender() == null) || (customer.getIdentity() == null)
                || (customer.getMaritalStatus() == null)) {
            final String msg = "ERROR: invalid Customer data! Customer content is invalid";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
    }

    private static void validateSaleCreditCard(final PaymentMethod paymentMethod) {
        final CardPaymentDTO creditCard = paymentMethod.getCardPayment();
        if (creditCard == null) {
            final String msg = "ERROR: invalid CardPaymentDTO data! CardPaymentDTO is null!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if (creditCard.getCardSecurityCode() == null) {
            final String msg = "ERROR: invalid CardPaymentDTO data! CardPaymentDTO.cardSecurityCode is null!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if (creditCard.getCardValue() == null) {
            final String msg = "ERROR: invalid CardPaymentDTO data! CardPaymentDTO.cardValue is null!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if (creditCard.getCardNumber() == null) {
            final String msg = "ERROR: invalid CardPaymentDTO data! CardPaymentDTO.cardNumber is null!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        validateSaleCreditCardContent(creditCard);
    }

    private static void validateSaleCreditCardContent(final CardPaymentDTO creditCard) {
        if ((creditCard.getCardDisplayName() == null) || (creditCard.getCardValidity() == null)
                || (creditCard.getCardFlag() == null)) {
            final String msg = "ERROR: invalid CardPaymentDTO data! CardPaymentDTO=[" + creditCard + "] is invalid!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
    }

}
