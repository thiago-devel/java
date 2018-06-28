package br.com.resseler.directsales.sales.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class does the integration with esb-billingClient
 *
 * @author a42239
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
        "squid:S1200" }, justification = "Nivel minimo de acoplamento ja foi atingido. O alto numero de "
                + "imports de classes se deve ao numero da objectos (stubs) necessarios para realizar a "
                + "comunicacao com o esb-billing.")
public final class PaymentIntegrationBilling {

    private static final String MAGAZINE_LUIZA_PARTNER_CODE = "MAGAZINE LUIZA";
    private static final String ENTERPRISE_CODE_MAGAZINE_LUIZA_VIDA = "VIDA";
    private static final Logger LOG = LogManager.getLogger(PaymentIntegrationBilling.class);

    private PaymentIntegrationBilling() {
    }

    /**
     * Processa Cartão de Crédito
     * 
     * @param paymentType
     *
     */
    public static void processCreditCardPaymentType(
            final /*PaymentType*/Object paymentType) {
/*
        final ProcessCardPaymentInType bodyPayloadRequest = new ProcessCardPaymentInType();
        bodyPayloadRequest.setPayment(paymentType);

        ProcessCardPaymentOUTType bodyPayloadResponse = null;

        try {
            LOG.debug("Preparing to call Billing Service with Resquest=["
                    + bodyPayloadRequest + "]");
            bodyPayloadResponse = BillingClient
                    .callProcessCardPayment(bodyPayloadRequest);
        } catch (final FuncFaultMessage e) {
            String msg = "ERROR: when call Billing Service FuncFault happened!";
            final FunctionalBaseFaultType fbft = e.getFaultInfo();
            if (fbft != null) {
                msg = msg.concat("[" + e.getFaultInfo().getDetail() + "]");
            }
            LOG.error(msg, e);
            throw new PaymentIntegrationException(msg, e,
                    ExceptionsConstants.CODE_FUNCFAULTMESSAGE,
                    ErrorType.BUSINESS);
        } catch (final TechFaultMessage e) {
            String msg = "ERROR: when call Billing Service TechFault happened!";
            final TechnicalBaseFaultType tbft = e.getFaultInfo();
            if (tbft != null) {
                msg = msg.concat("[" + e.getFaultInfo().getDetail() + "]");
            }
            LOG.error(msg, e);
            throw new PaymentIntegrationException(msg, e,
                    ExceptionsConstants.CODE_TECHFAULTMESSAGE,
                    ErrorType.BUSINESS);
        } catch (final WebServiceException | MalformedURLException e) {
            final String msg = "ERROR: when call Billing Service WebServiceException happened!"
                    + e.getMessage();
            LOG.error(msg, e);
            throw new WebServiceException(msg, e);
        }

        LOG.debug(bodyPayloadResponse.getPaymentResult().get(0)
                .getTransactionCode() + " - "
                + bodyPayloadResponse.getPaymentResult().get(0)
                        .getMessageResult());
        LOG.debug("Billing Service Response=[" + bodyPayloadResponse + "]");
        */
    }

    /**
     * Monta o obj PaymentType
     * 
     * @param billingPaymentType
     * @return PaymentType
     *
     */
    public static /*PaymentType*/Object mountBillingPaymentType(
            final /*BillingPaymentType*/Object billingPaymentType) {
/*
        final PaymentMethodType paymentMethodType = new PaymentMethodType();
        final CardPaymentType cardData = new CardPaymentType();
        final CardsPaymentType cardsData = new CardsPaymentType();
        cardData.setSecurityCode(billingPaymentType.getCodigoDeSeguranca());

        cardData.setCardValue(billingPaymentType.getValorCobrancaCartao());

        final InstallmentsType installmentsType = new InstallmentsType();
        installmentsType
                .setInstallmentNumber(billingPaymentType.getNumeroParcelas());
        installmentsType.setInstallmentValue(
                billingPaymentType.getValorCobrancaCartao());
        cardData.setInstallments(installmentsType);

        final PremiumType premiumType = new PremiumType();
        final String premium = String.valueOf(billingPaymentType.getPremio());
        premiumType.setPremiumValue(premium);

        final PaymentMethodDataBuilder builder = new PaymentMethodDataBuilder();
        builder.setCartaoNumero(billingPaymentType.getCartaoNumero());
        builder.setCartaoNome(billingPaymentType.getCartaoNome());
        builder.setCartaoValidade(billingPaymentType.getCartaoValidade());
        builder.setCartaoCodigoBandeira(
                billingPaymentType.getCartaoCodigoBandeira());
        builder.setPremio(billingPaymentType.getPremio());
        builder.setPaymentMethodType(paymentMethodType);
        builder.setCardData(cardData);
        builder.setCardsData(cardsData);
        final PaymentMethodData paymentMethodData = builder.build();
        final PaymentMethodsType paymentMethodsType = mountBillingPaymentMethodsType(
                paymentMethodData);

        final NaturalPersonInputType personData = mountBillingPersonType(
                billingPaymentType.getDataDeNascimentoDoSegurado(),
                billingPaymentType.getPersonType().getNomeDoSegurado(),
                billingPaymentType.getPersonType().getSexo(),
                billingPaymentType.getPersonType().getCivilState());

        final EmailAddressInputType emailData = new EmailAddressInputType();
        emailData.setEmailName(billingPaymentType.getPersonType().getEmail());

        final AddressesType addressesData = mountBillingAddressesType(
                billingPaymentType.getAddressType().getLogradouro(),
                billingPaymentType.getAddressType().getNumeroEndereco(),
                billingPaymentType.getAddressType().getCidade(),
                billingPaymentType.getAddressType().getCEP(),
                billingPaymentType.getAddressType().getBairro(),
                billingPaymentType.getAddressType().getUF(),
                billingPaymentType.getAddressType().getComplemento());

        final QName qname = new QName(
                "http://ea.assurance.bnpparibas.com/internal/schema/fr/common/v1_1_9",
                "phoneType");
        final PhoneType phoneTypeResidencial = mountBillingPhoneAddressResidential(
                billingPaymentType.getContactType()
                        .getDddEtelefoneResidencial(),
                qname);
        final PhoneType phoneTypeCelular = mountBillingPhoneAddressCell(
                billingPaymentType.getContactType().getDddEtelefoneCelular(),
                qname);
        final PhoneType phoneTypeComercial = mountBillingPhoneAddressComercial(
                billingPaymentType.getContactType().getDddETelefoneComercial(),
                qname);

        final PersonIdentificationType identification = new PersonIdentificationType();
        identification.setCode("BR_CPF");
        identification
                .setValue(billingPaymentType.getPersonType().getCpfCnpj());
        final PersonIdentificationsType identifications = new PersonIdentificationsType();
        identifications.getPersonIdentification().add(identification);

        final MasterPolicyType masterPolicyType = new MasterPolicyType();
        masterPolicyType
                .setMasterPolicyCode(billingPaymentType.getProductMasterCode());

        final PartnerType partnerType = new PartnerType();
        partnerType.setPartnerCode(MAGAZINE_LUIZA_PARTNER_CODE);

        final EnterpriseType enterprise = new EnterpriseType();
        enterprise.setEnterpriseCode(ENTERPRISE_CODE_MAGAZINE_LUIZA_VIDA);

        final ContractType contractType = new ContractType();
        final Long lContractNumber = Long
                .valueOf(billingPaymentType.getContractNumber());
        final BigInteger bContractNumber = BigInteger.valueOf(lContractNumber);
        contractType.setContractNumber(bContractNumber);

        final PaymentType paymentType = new PaymentType();

        paymentType.setPaymentMethods(paymentMethodsType);
        paymentType.setPersonData(personData);
        paymentType.setEmailData(emailData);
        paymentType.setAddressesData(addressesData);
        paymentType.getPhonesData().add(phoneTypeResidencial);
        paymentType.getPhonesData().add(phoneTypeCelular);
        paymentType.getPhonesData().add(phoneTypeComercial);
        paymentType.setPersonIdentificationsData(identifications);
        paymentType.setMasterPolicy(masterPolicyType);
        paymentType.setPartner(partnerType);
        paymentType.setEnterprise(enterprise);
        paymentType.setContract(contractType);
        paymentType.setPremium(premiumType);

 		*/
        return null;//paymentType;
    }

    /*
    private static PhoneType mountBillingPhoneAddressComercial(
            final String dddETelefoneComercial, final QName qname) {
        final PhoneAddressInputType phoneComercial = new PhoneAddressInputType();
        phoneComercial.setUseCntxt("Comercial");
        phoneComercial.setLineType(dddETelefoneComercial);

        final JAXBElement<PhoneAddressInputType> ePhoneTypeComercial = new JAXBElement(
                qname, PhoneAddressInputType.class, phoneComercial);
        final PhoneType phoneTypeComercial = new PhoneType();
        phoneTypeComercial.setPhoneType(ePhoneTypeComercial);
        return phoneTypeComercial;
    }

    private static PhoneType mountBillingPhoneAddressCell(
            final String dddEtelefoneCelular, final QName qname) {
        final PhoneAddressInputType phoneCelular = new PhoneAddressInputType();
        phoneCelular.setUseCntxt("Celular");
        phoneCelular.setLineType(dddEtelefoneCelular);

        final JAXBElement<PhoneAddressInputType> ePhoneTypeCelular = new JAXBElement(
                qname, PhoneAddressInputType.class, phoneCelular);
        final PhoneType phoneTypeCelular = new PhoneType();
        phoneTypeCelular.setPhoneType(ePhoneTypeCelular);
        return phoneTypeCelular;
    }

    private static PhoneType mountBillingPhoneAddressResidential(
            final String dddEtelefoneResidencial, final QName qname) {
        final PhoneAddressInputType phoneResidencial = new PhoneAddressInputType();
        phoneResidencial.setUseCntxt("Residencial");
        phoneResidencial.setLineType(dddEtelefoneResidencial);

        final JAXBElement<PhoneAddressInputType> ePhoneTypeResidencial = new JAXBElement(
                qname, PhoneAddressInputType.class, phoneResidencial);
        final PhoneType phoneTypeResidencial = new PhoneType();
        phoneTypeResidencial.setPhoneType(ePhoneTypeResidencial);
        return phoneTypeResidencial;
    }

    private static AddressesType mountBillingAddressesType(
            final String logradouro, final String numeroEndereco,
            final String cidade, final String CEP, final String bairro,
            final String UF, final String complemento) {
        final PostalAddressInputType addressData = new PostalAddressInputType();
        addressData.getLine().add(logradouro + ", " + numeroEndereco);
        addressData.setCityName(cidade);
        addressData.setZipOrPostCodeNumb(CEP);

        final AddressType addressType = new AddressType();
        addressType.setAddressData(addressData);
        addressType.setAddressThirdLineName(complemento);
        addressType.setFederativeUnit(UF);
        addressType.setNeighborhoodName(bairro);

        final AddressesType addressesData = new AddressesType();
        addressesData.getAddressType().add(addressType);
        return addressesData;
    }

    private static NaturalPersonInputType mountBillingPersonType(
            final Calendar dataDeNascimentoDoSegurado,
            final String nomeDoSegurado, final String sexo,
            final String civilState) {
        final NaturalPersonInputType personData = new NaturalPersonInputType();
        personData.setUsdName(nomeDoSegurado);
        personData.setBrthDate((dataDeNascimentoDoSegurado != null)
                ? dataDeNascimentoDoSegurado : null);
        personData.setMaritalStatus(
                (civilState != null) ? civilState.toUpperCase() : null);
        personData.setGendr(sexo);
        return personData;
    }

    private static PaymentMethodsType mountBillingPaymentMethodsType(
            final PaymentMethodData paymentMethodData) {
        final PaymentCardDataType paymentCardDataType = new PaymentCardDataType();
        paymentCardDataType.setNumb(paymentMethodData.getCartaoNumero());
        paymentCardDataType.setHoldrName(paymentMethodData.getCartaoNome());
        paymentCardDataType
                .setValdtyEndDate(paymentMethodData.getCartaoValidade());

        final String cartaoCodigoBandeira = paymentMethodData
                .getCartaoCodigoBandeira();
        if (cartaoCodigoBandeira == null) {
            final FunctionalBaseFaultType baseFault = new FunctionalBaseFaultType();
            baseFault.setDetail(
                    "Bandeira {" + cartaoCodigoBandeira + "} invalida");
            final FuncFaultMessage funcFaultMessageEx = new FuncFaultMessage(
                    "TEF Validation Fail!", baseFault);

            throw new IllegalArgumentException(funcFaultMessageEx);
        }
        paymentCardDataType.setCardType(cartaoCodigoBandeira);

        final CardPaymentType cardData = paymentMethodData.getCardData();
        cardData.getCard().add(paymentCardDataType);

        final CardsPaymentType cardsData = paymentMethodData.getCardsData();
        cardsData.getCardPayment().add(cardData);

        final PaymentMethodType paymentMethodType = paymentMethodData
                .getPaymentMethodType();
        paymentMethodType.setCardsPayment(cardsData);
        final BigDecimal premio = paymentMethodData.getPremio();
        paymentMethodType.setAmount(premio.toString());

        final PaymentMethodsType paymentMethodsType = new PaymentMethodsType();
        paymentMethodsType.getPaymentMethodType().add(paymentMethodType);
        return paymentMethodsType;
    }
    */
}
