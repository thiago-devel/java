package br.com.resseler.directsales.commons.persistence.dao.test;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resseler.directsales.commons.persistence.dao.impl.InsuredDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO;
import br.com.resseler.directsales.commons.persistence.dao.test.config.TestDAOMockAppConfig;
import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.enums.AddressType;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.Partner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDAOMockAppConfig.class })
public class InsuredDAOTest {

	@Autowired
	private InsuredDAO daoInsured;
	@Autowired
	private SystemPersonDAO daoSPerson;

	public static final Long DEFAULT_PARTNER_ID = 142L;
	private static final String COMPRADOR = "COMPRADOR";
	private static final String customerCPF = "6600693851";
	private static final String customerWillian = "WILLIAM FEIJO SILVA";
	private static Logger LOG = null;

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

		LOG = LogManager.getContext().getLogger("br.com");

	}

	@Before
	public void setup() {
		Assert.assertNotNull(daoInsured);
		Assert.assertNotNull(daoSPerson);
	}

	@Test
	public void test01AnyLogger() {	
		LOG.info("OK, Logged!");
	}
	
	@Test
	public void testPersisteAndRetrieveCustomer() {
		
		final Customer customer = new Customer();
		customer.setID(10601L);
		final Partner partner = new Partner();
		partner.setID(DEFAULT_PARTNER_ID);
		customer.setPartner(partner);
		final LoginDTO loginC = new LoginDTO();
		loginC.setID(24L);
		customer.setLogin(loginC);
		customer.setFullName(customerWillian);
		customer.setSUP(1);
		final IdentityDTO cCPF = new IdentityDTO();
		cCPF.setDocumentType(Document.CPF);
		cCPF.setDocumentValue(customerCPF);
		customer.setIdentity(cCPF);
		final Calendar dataNascimentoC = new GregorianCalendar();
		dataNascimentoC.set(Calendar.YEAR, 1979);
		dataNascimentoC.set(Calendar.MONTH, 6);
		dataNascimentoC.set(Calendar.DAY_OF_MONTH, 19);
		customer.setBirthDate(dataNascimentoC);
		customer.setDrtID(315);
		customer.setMlID(99059);
		customer.setRole(COMPRADOR);
		customer.setType(1);
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

		final Number key = daoInsured.persist(customer);

		Assert.assertNotNull(key);
	}
}
