package br.com.resseler.directsales.commons.persistence.dao.test;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ENTERPRISE_TYPE_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_COD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_CAP_SER_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_FLG_CAP_SER;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_FLG_CRT_NBR_FT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_VL_COMIS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_CERTIF_PRINT_FG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_CERT_PREFIX;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_COD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_EFF_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_EMP_CAP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_INS_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MAX_CERT_CPF;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MAX_INSURED_AGE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MIN_INSURED_AGE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MP_NO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_PLAN_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_PRZ_VIG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_SLM_PRT_PCT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_SLM_PRT_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_WORKFLOW_STEPS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TEF_AGREEMENT_ID;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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

import br.com.resseler.directsales.commons.persistence.dao.impl.ProductInsuranceDAO;
import br.com.resseler.directsales.commons.persistence.dao.test.config.TestDAOMockAppConfig;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDAOMockAppConfig.class })
public class ProductDAOTest {

    private static final String PRODUCT_CARTAO_SUPER_PROTEGIDO = "Seguro Luiza Bolsa Protegida";
    public static final int PRODUCT_CARTAO_SUPER_PROTEGIDO_ID = 25;
    private static final String PRODUTO_PREFIXO = "1095";
    @Autowired
    private ProductInsuranceDAO daoProduct;
    @Autowired
    private javax.sql.DataSource dataSourceDirectSales;
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
        Assert.assertNotNull(dataSourceDirectSales);
    }

    @Test
    public void testPersistProductAndRetrievePrefix() {
        final InsuranceCertificate certificate = gravaProduto(daoProduct);
        final Long productID = (certificate.getProduct() == null) ? null : certificate.getProduct().getID();

        Assert.assertNotNull(productID);
        Assert.assertEquals(productID.intValue(), PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);

        final String prefixoProduto = daoProduct.retrieveProductPrefix(productID);
        Assert.assertNotNull(prefixoProduto);
        Assert.assertEquals(PRODUTO_PREFIXO, prefixoProduto);
    }

    @Test
    public void testPersistAndRetrieveProduct() {
        final InsuranceDTO product = daoProduct.retrieveProduct(Integer.valueOf(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID).longValue());

        Assert.assertNotNull(product);
        Assert.assertEquals(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID, product.getID().intValue());
        Assert.assertEquals(PRODUCT_CARTAO_SUPER_PROTEGIDO, product.getDescription());
    }

    public static Map<String, Object> mountProductParameters() {
        final Map<String, Object> parans = new HashMap<String, Object>();
        parans.put(PRD_ID, PRODUCT_CARTAO_SUPER_PROTEGIDO_ID);
        parans.put(PARTNER_ID, 142);
        parans.put(PRD_COD, 173);
        parans.put(PRD_NME, PRODUCT_CARTAO_SUPER_PROTEGIDO);
        final Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 46);
        calendar.set(Calendar.SECOND, 49);
        parans.put(PRD_EFF_DT, calendar.getTime());
        parans.put("PRD_EXP_DT", null);
        parans.put(PRD_INS_DTM, calendar.getTime());
        parans.put("PRD_INS_USER_ID", null);
        parans.put("PRD_UPD_DTM", null);
        parans.put("PRD_UPD_USER_ID", null);
        parans.put(PRD_SLM_PRT_VL, new BigDecimal(11.89));
        parans.put(PRD_SLM_PRT_PCT, new Double(0.00000));
        parans.put(PRD_PRZ_VIG, 60);
        parans.put(PRD_AGR_ID, 1);
        parans.put(PRD_MP_NO, 95169);
        parans.put(PRD_AGR_VL_COMIS, new Double(0.00));
        parans.put(PRD_AGR_FLG_CAP_SER, 'S');
        parans.put(PRD_AGR_CAP_SER_VL, new Double(0.00));
        parans.put(PRD_PLAN_ID, 384);
        parans.put(PRD_CERTIF_PRINT_FG, "S");
        parans.put(PRD_WORKFLOW_STEPS, "10111");
        parans.put(PRD_MAX_CERT_CPF, 1);
        parans.put(PRD_MAX_INSURED_AGE, 65);
        parans.put(PRD_MIN_INSURED_AGE, 18);
        parans.put(PRD_EMP_CAP, 142);
        parans.put(PRD_CERT_PREFIX, PRODUTO_PREFIXO);
        parans.put(PRD_AGR_FLG_CRT_NBR_FT, 'N');
        parans.put("PRD_SUM_CAR_VL", null);
        parans.put(PARTNER_COD, 142);
        parans.put("PRD_ELEGIABLE_CAR_MAX_AGE", null);
        parans.put("PRD_ELEGIABLE_CAR_MAX_FIPE_PRICE", null);
        parans.put("PRD_MIN_FINANCING_VALUE", null);
        parans.put("PRD_MAX_FINANCING_VALUE", null);
        parans.put(ENTERPRISE_TYPE_ID, 1);
        parans.put(TEF_AGREEMENT_ID, 1);

        return parans;
    }

    public static InsuranceCertificate gravaProduto(final ProductInsuranceDAO daoProduct) {
        final Map<String, Object> parans = mountProductParameters();
        final InsuranceCertificate certificate = new InsuranceCertificate();

        final InsuranceDTO product = new InsuranceDTO();
        product.setDescription(PRODUCT_CARTAO_SUPER_PROTEGIDO);
        product.setID(Integer.valueOf(PRODUCT_CARTAO_SUPER_PROTEGIDO_ID).longValue());
        certificate.setProduct(product);

        final Number key = daoProduct.persist(certificate, parans);
        Assert.assertNotNull(key);

        return certificate;
    }
}
