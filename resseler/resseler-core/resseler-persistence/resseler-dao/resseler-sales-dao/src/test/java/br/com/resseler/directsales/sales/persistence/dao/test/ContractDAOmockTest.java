package br.com.resseler.directsales.sales.persistence.dao.test;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Calendar;

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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDAO;
import br.com.resseler.directsales.sales.persistence.dao.test.config.TestSalesDELETEDAOMockAppConfig;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

@org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { TestSalesDELETEDAOMockAppConfig.class })
public class ContractDAOmockTest {

    private static final String SECURITY_KEY_ID_DEFAULT = "kid1";
    private static final String CARTAO_ENCRIPTADO = "6D97C368B561CC3C8F323230F7744D2058A7DCDF999E912B";
    @Autowired
    private SalesDAO daoSales;

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
        Assert.assertNotNull(daoSales);
    }

    @Test
    public void test() {
        // DO NOTHING
    }

    // @Test
    public void testCallServiceUpdatePaymentMethod() {
        final CardPaymentDTO creditCard = createCardEncriptedForTestCheckUpdateMethod();
        final PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardPayment(creditCard);
        final IdentityDTO identitySalesman = new IdentityDTO();
        identitySalesman.setDocumentType(Document.CPF);
        identitySalesman.setDocumentValue("15583435801");
        final Salesman salesman = new Salesman();
        salesman.setIdentity(identitySalesman);
        final InsuranceCertificate certificate = new InsuranceCertificate();
        certificate.setSalesman(salesman);
        certificate.setPaymentMethod(paymentMethod);
        certificate.setContractNumber("1095713643370");

        daoSales.updatePaymentMethodContract(certificate, SECURITY_KEY_ID_DEFAULT);
    }

    private CardPaymentDTO createCardEncriptedForTestCheckUpdateMethod() {
        final CardPaymentDTO creditCard = new CardPaymentDTO();
        creditCard.setCardDisplayName("MARIA L B SANTOS");
        creditCard.setCardFlag("LUIZA");
        // creditCard.setCardNumber("5305993016354689");
        creditCard.setCardNumber(CARTAO_ENCRIPTADO);
        creditCard.setCardSecurityCode("000");
        creditCard.setCardValue(BigDecimal.ONE);
        final Calendar cardValidityDate = Calendar.getInstance();
        cardValidityDate.set(Calendar.YEAR, 2018);
        creditCard.setCardValidity(cardValidityDate);
        return creditCard;
    }
}
