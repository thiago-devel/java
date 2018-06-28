package br.com.resseler.directsales.sales.persistence.dao.test;

import java.nio.charset.Charset;

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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resseler.directsales.commons.persistence.dao.impl.SalesmanDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO;
import br.com.resseler.directsales.sales.persistence.dao.test.config.TestSalesDELETEDAOMockAppConfig;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { TestSalesDELETEDAOMockAppConfig.class })
public class SalesmanDAOdbTest {

    @Autowired
    private SalesmanDAO daoSalesman;
    @Autowired
    private SystemPersonDAO daoSPerson;
    private static final String salesmanAline = "ALCIDES ROSSO JUNIOR";
    private static final String salesmanCPF = "15583435801";
    private static final String VENDEDOR = "VENDEDOR";

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
        Assert.assertNotNull(daoSalesman);
        Assert.assertNotNull(daoSPerson);
    }

    @Test
    @Ignore
    public void testRetrieveASingleSalesmanForMoreThenOneCPF() {
        final Salesman salesman = daoSalesman.retrieveSalesmanBy(salesmanCPF);

        Assert.assertNotNull(salesman);
    }
}
