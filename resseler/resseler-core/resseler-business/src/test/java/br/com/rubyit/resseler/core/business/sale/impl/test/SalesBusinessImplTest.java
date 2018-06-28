package br.com.rubyit.resseler.core.business.sale.impl.test;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CREDIT_CARD_PAYMENT_TYPE;
import static br.com.rubyit.resseler.core.business.sale.impl.test.CardPaymentTest.CPF_EXEMPLO;
import static br.com.rubyit.resseler.core.business.sale.impl.test.CardPaymentTest.NOME_CARTAO_EXEMPLO;
import static br.com.rubyit.resseler.core.business.sale.impl.test.CardPaymentTest.montaCartaoParaTestes;
import static br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants.MSG_1_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
import static br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants.MSG_3_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
import static org.hamcrest.CoreMatchers.startsWith;

import java.util.Calendar;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.dto.PhoneDTO;
import br.com.rubyit.resseler.commons.kernel.enums.AddressType;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.commons.kernel.enums.Gender;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.commons.kernel.enums.PhoneType;
import br.com.rubyit.resseler.core.business.sale.impl.SaleBusinessImpl;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestBusinessAppConfig.class })
@Ignore
public class SalesBusinessImplTest {

    private static final String PASSWORD_LOGIN = "0000100";
    private static final String USERNAME_LOGIN = PASSWORD_LOGIN;
    private static final Long DEFAULT_PARTNER = 142L;
    public static final Long PRODUCT_CARTAO_SUPER_PROTEGIDO_ID = 25L;
    public static final Long PRODUCT_PROTECAO_PREMIADA = 26L;
    @Autowired
    private SaleBusinessImpl impl;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDoSale() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue(CPF_EXEMPLO);
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("SP");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        /*
        final InsuranceCertificate certificate = impl.doSale(sale);
        Assert.notNull(certificate);
        Assert.isTrue(certificate.getProduct().getStatus() == InsuranceStatus.ACTIVE);
        System.out.println("CERTIFICATE GENERATED=[" + certificate + "]");
        */
    }

    @Test
    public void testCanNotDoSaleToCustomerThatAlreadHaveACertificateProduct25() {
        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("05833698049");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("SP");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = MSG_1_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(InsuranceCertificateException.class));
        // thrown.expect(DoSaleException.class);

        impl.doSale(sale);
    }

    @Test
    public void testCanNotDoSaleToCustomerThatAlreadHaveACertificateProduct26() {
        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("13702902848");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("SP");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_PROTECAO_PREMIADA);
        sale.setProduct(product);

        final String messageException = MSG_3_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(InsuranceCertificateException.class));
        // thrown.expect(DoSaleException.class);

        impl.doSale(sale);
    }

    @Test(expected = ValidationException.class)
    public void testDoSaleAddress() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("78909345306");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail(
                "Rua blablabla  MAX LENGHT PARA ENDEREÇOS MUITO GRANDE bla bla bla bla bla bla bla bla bla bla bla bla bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("SP");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        impl.doSale(sale);
    }

    @Test
    public void testeCanNotDoSaleWithCardNameWithNumbers() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        final String cardDisplayName = "BOLINHA 123 BUM";
        creditCard.setCardDisplayName(cardDisplayName);
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("50454585233");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("SP");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = "ERROR = O campo Nome do Cartão [" + cardDisplayName
                + "], não pode conter dígitos de caracteres.";
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(IllegalArgumentException.class));
        thrown.expect(ValidationException.class);

        impl.doSale(sale);
    }

    @Test
    public void testeCanNotDoSaleWithAddressDetailBiggerThen79() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        final String cardDisplayName = "BOLINHA BUM";
        creditCard.setCardDisplayName(cardDisplayName);
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("50454585233");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail(
                "Bom bolinha do norte atlantico oriental Bom bolinha do norte atlantico oriental Bom bolinha do norte atlantico oriental");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("SP");
        addressRes.setNeighborhood("Bom bolinha do norte");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = "O campo Endereço é obrigatório e não pode conter mais que 79 caracteres.";
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(IllegalArgumentException.class));
        thrown.expect(ValidationException.class);

        impl.doSale(sale);
    }

    @Test
    public void testeCanNotDoSaleWithNeighborhoodBiggerThen30() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        final String cardDisplayName = "BOLINHA BUM";
        creditCard.setCardDisplayName(cardDisplayName);
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("50454585233");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("SP");
        addressRes.setNeighborhood("Bom bolinha do norte atlantico oriental");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = "O campo Bairro é obrigatório e não pode conter mais que 30 caracteres.";
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(IllegalArgumentException.class));
        thrown.expect(ValidationException.class);

        impl.doSale(sale);
    }

    @Test
    public void testeCanNotDoSaleWithCityBiggerThen2() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        final String cardDisplayName = "BOLINHA BUM";
        creditCard.setCardDisplayName(cardDisplayName);
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("50454585233");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("Bom bolinha do norte atlantico oriental");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = "O campo Cidade é obrigatório e não pode conter mais que 30 caracteres.";
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(IllegalArgumentException.class));
        thrown.expect(ValidationException.class);

        impl.doSale(sale);
    }

    @Test
    public void testeCanNotDoSaleWithAddressReferenceBiggerThen20() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        final String cardDisplayName = "BOLINHA BUM";
        creditCard.setCardDisplayName(cardDisplayName);
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("50454585233");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Bom bolinha do norte atlantico oriental");
        addressRes.setCity("BOM");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = "O campo Complemento não pode conter mais que 20 caracteres.";
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(IllegalArgumentException.class));
        thrown.expect(ValidationException.class);

        impl.doSale(sale);
    }

    @Test
    public void testeCanNotDoSaleWithStateBiggerThen20() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        final String cardDisplayName = "BOLINHA BUM";
        creditCard.setCardDisplayName(cardDisplayName);
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("50454585233");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("BOM");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("Bom bolinha do norte atlantico oriental");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = "O campo Estado é obrigatório e não pode conter mais que 2 caracteres.";
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(IllegalArgumentException.class));
        thrown.expect(ValidationException.class);

        impl.doSale(sale);
    }

    @Test
    public void testeCanNotDoSaleWithNumberBiggerThen14() {

        final Sale sale = new Sale();
        final PaymentMethod paymentMethod = new PaymentMethod();
        final CardPaymentDTO creditCard = montaCartaoParaTestes();
        final String cardDisplayName = "BOLINHA BUM";
        creditCard.setCardDisplayName(cardDisplayName);
        paymentMethod.setCardPayment(creditCard);
        paymentMethod.setPaymentType(CREDIT_CARD_PAYMENT_TYPE);
        sale.setPaymentMethod(paymentMethod);
        final Customer customer = new Customer();
        final Calendar birtDate = Calendar.getInstance();
        birtDate.set(Calendar.YEAR, 1984);
        customer.setBirthDate(birtDate);
        customer.setFullName(NOME_CARTAO_EXEMPLO);
        customer.setGender(Gender.MALE);
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("50454585233");
        customer.setIdentity(identity);
        customer.setMaritalStatus(MaritalStatus.MARRIED);
        final AddressDTO addressRes = new AddressDTO();
        addressRes.setAddressDetail("Rua bla");
        addressRes.setAddressNumber("208Bom bolinha do norte atlantico oriental");
        addressRes.setAddressPostalCode("06871120");
        addressRes.setAddressReference("Qualquer");
        addressRes.setCity("BOM");
        addressRes.setNeighborhood("Bom");
        addressRes.setState("SP");
        addressRes.setType(AddressType.RESIDENTIAL);
        final PhoneDTO phoneRes = new PhoneDTO();
        phoneRes.setType(PhoneType.RESIDENTIAL);
        phoneRes.setPhoneNumber("1143216363");
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
        contactDTO.setPhone(phoneRes);
        customer.retrieveContacts().add(contactDTO);
        sale.setCustomer(customer);
        final Salesman salesman = new Salesman();
        final LoginDTO login = new LoginDTO();
        login.setUsername(USERNAME_LOGIN);
        login.setPassword(PASSWORD_LOGIN);
        salesman.setOperatorName("Blah");
        salesman.setLogin(login);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("6600693851");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        final String messageException = "O campo Número do Endereço é obrigatório e não pode conter mais que 14 caracteres.";
        thrown.expectMessage(startsWith(messageException));
        // thrown.expectCause(isA(IllegalArgumentException.class));
        thrown.expect(ValidationException.class);

        impl.doSale(sale);
    }
}
