package br.com.rubyit.resseler.core.persistence.dao.test;

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
import org.springframework.beans.factory.annotation.Autowired;

import br.com.rubyit.resseler.commons.core.base.tests.TestPrototypes;
import br.com.rubyit.resseler.persistence.dao.BrandDAO;
import br.com.rubyit.resseler.persistence.dao.CategoryDAO;
import br.com.rubyit.resseler.persistence.dao.CompanyDAO;
import br.com.rubyit.resseler.persistence.dao.ProductAttributesDAO;
import br.com.rubyit.resseler.persistence.dao.ProductDAO;
import br.com.rubyit.resseler.persistence.dao.ProductOfTheCompanyDAO;
import br.com.rubyit.resseler.persistence.mapper.InnerOuterMapper;
import ma.glasnost.orika.MapperFacade;

public abstract class BaseProductOfTheCompanyTest extends TestPrototypes {
	
	@Autowired
	protected ProductDAO productDAO;
	@Autowired
	protected CategoryDAO categoryDAO;
	@Autowired
	protected BrandDAO brandDAO;
	@Autowired
	protected ProductAttributesDAO productAttributesDAO;
	@Autowired
	protected CompanyDAO companyDAO;
	@Autowired
	protected ProductOfTheCompanyDAO productOfTheCompanyDAO;

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

	public MapperFacade instanceMapperFactoryAndFacade() {
		return InnerOuterMapper.getInstance().getMapper();
	}
}
