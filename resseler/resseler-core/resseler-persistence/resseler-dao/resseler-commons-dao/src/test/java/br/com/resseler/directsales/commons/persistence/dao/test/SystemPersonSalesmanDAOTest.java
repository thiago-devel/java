package br.com.resseler.directsales.commons.persistence.dao.test;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO;
import br.com.resseler.directsales.commons.persistence.dao.test.config.TestDAOMockAppConfig;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDAOMockAppConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemPersonSalesmanDAOTest {

    public static final int DEFAULT_PARTNER_ID = 142;
    private static final String VENDEDOR = "VENDEDOR";
    @Autowired
    private SystemPersonDAO daoSPerson;
    @Autowired
    private javax.sql.DataSource dataSourceDirectSales;
    private static final String salesmanCPF = "22173020862";
    private static final String salesmanAline = "ALINE TAVEIRA CALDAS";
    private Partner partner;

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
        Assert.assertNotNull(dataSourceDirectSales);
        Assert.assertNotNull(daoSPerson);
    }

    @Test
    public void retrivePersonSalesmanAline() {
        final Number key = persistPersonSalesman(partner, daoSPerson);
        Assert.assertNotNull(key);

        final List<SystemPerson> personL = daoSPerson.retrieveBy(salesmanCPF);
        final SystemPerson person = personL.get(0);
        Assert.assertNotNull(person);
        Assert.assertEquals(salesmanAline, person.getFullName());
        Assert.assertEquals(MaritalStatus.SINGLE, person.getMaritalStatus());
        Assert.assertEquals(salesmanCPF, person.getIdentity().getDocumentValue());
    }

    public static Long persistPersonSalesman(final Partner partner, final SystemPersonDAO daoSPerson) {
        final SystemPerson salesman = new SystemPerson();
        salesman.setID(10527L);
        salesman.setPartner(partner);
        final LoginDTO loginS = new LoginDTO();
        loginS.setID(68L);
        salesman.setLogin(loginS);
        salesman.setFullName(salesmanAline);
        salesman.setSUP(1);
        final IdentityDTO sCPF = new IdentityDTO();
        sCPF.setDocumentType(Document.CPF);
        sCPF.setDocumentValue(salesmanCPF);
        salesman.setIdentity(sCPF);
        final Calendar dataNascimentoS = new GregorianCalendar();
        dataNascimentoS.set(Calendar.YEAR, 1982);
        dataNascimentoS.set(Calendar.MONTH, 11);
        dataNascimentoS.set(Calendar.DAY_OF_MONTH, 28);
        salesman.setBirthDate(dataNascimentoS);
        salesman.setDrtID(3653);
        salesman.setMlID(3653);
        salesman.setRole(VENDEDOR);
        salesman.setType(1);
        salesman.setMaritalStatus(MaritalStatus.SINGLE);

        final Long key = daoSPerson.persist(salesman);

        return key;
    }
}
