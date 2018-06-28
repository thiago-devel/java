package br.com.resseler.directsales.sales.persistence.dao.test;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CREDIT_CARD_PAYMENT_TYPE;
import static br.com.resseler.directsales.sales.persistence.dao.test.CardPaymentTest.CPF_EXEMPLO;
import static br.com.resseler.directsales.sales.persistence.dao.test.CardPaymentTest.NOME_CARTAO_EXEMPLO;
import static br.com.resseler.directsales.sales.persistence.dao.test.CardPaymentTest.montaCartaoParaTestes;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDAO;
import br.com.resseler.directsales.sales.persistence.dao.test.config.TestSalesDELETEDAOMockAppConfig;
import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.enums.AddressType;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.commons.kernel.enums.Gender;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

@org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestSalesDELETEDAOMockAppConfig.class })
public class SalesDAOTest {

    private static final String PASSWORD_LOGIN = "0000100";
    private static final String USERNAME_LOGIN = PASSWORD_LOGIN;
    private static final Long DEFAULT_PARTNER = 142L;
    public static final Long PRODUCT_CARTAO_SUPER_PROTEGIDO_ID = 25L;
    @Autowired
    private SalesDAO daoSale;

    static {
    	LoggerContext context = (LoggerContext) LogManager.getContext();
		Configuration config = context.getConfiguration();

		PatternLayout layout = PatternLayout.createLayout("%-6r [%p] %c - %m%n", null, null, Charset.defaultCharset(),
				false, false, null, null);
		Appender appender = ConsoleAppender.createAppender(layout, null, null, "CONSOLE_APPENDER", null, null);
		appender.start();
		AppenderRef ref = AppenderRef.createAppenderRef("CONSOLE_APPENDER", null, null);
		AppenderRef[] refs = new AppenderRef[] { ref };
		LoggerConfig loggerConfig = LoggerConfig.createLogger("false", Level.INFO, "CONSOLE_LOGGER", "br.com", refs,
				null, null, null);
		loggerConfig.addAppender(appender, null, null);

		config.addAppender(appender);
		config.addLogger("br.com", loggerConfig);
		context.updateLoggers(config);
    }

    @Before
    public void setup() {
        Assert.assertNotNull(daoSale);
    }

    @Test
    public void testProcessSale() {
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
        final ContactDTO contactDTO = new ContactDTO();
        contactDTO.setAddress(addressRes);
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
        identitySalesman.setDocumentValue("15583435801");
        salesman.setIdentity(identitySalesman);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER);
        salesman.setPartner(partner);
        sale.setSalesman(salesman);
        final InsuranceDTO product = new InsuranceDTO();
        product.setID(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        sale.setProduct(product);

        /*try {
        daoSale.process(sale);
        } catch (final Exception ex) {
            if ((ex instanceof TechFaultMessage) || (ex instanceof FuncFaultMessage)
                    || (ex instanceof MalformedURLException)) {
                // Faz nada. Falha na chamado para o Billing
                return;
            }
        }*/
    }

    @Test
    @Ignore
    public void testRetrieveActiveCertificatesWithCaptalSeries() {
        final String contractNumber = "1095827654909";
        final List<InsuranceCertificate> certificateList = daoSale.retrieveCertificateFor(contractNumber);

        Assert.assertNotNull(certificateList);
        Assert.assertEquals(certificateList.size(), 1);
        Assert.assertNotNull(certificateList.get(0).getID());
        Assert.assertNotNull(certificateList.get(0).getCapitalSeries());
        Assert.assertNotNull(certificateList.get(0).getCapitalSeries().getCapitalNumber());
    }
}
