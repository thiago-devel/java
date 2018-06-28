package br.com.rubyit.resseler.core.persistence.dao.potc.test;

import java.nio.charset.Charset;

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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.ProductOfTheCompany;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.persistence.config.dao.test.TestDBPostgresDAOAppConfig;
import br.com.rubyit.resseler.core.persistence.dao.test.BaseProductOfTheCompanyTest;
import br.com.rubyit.resseler.persistence.entity.ProductOfTheCompanyEntity;

// @org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDBPostgresDAOAppConfig.class })
public class ProductOfTheCompanyDAOPostgresDAOTest extends ProductOfTheCompanyDAOBaseDAOTest {

    @Before
    public void setup() {
    	LOG = LogManager.getLogger(ProductOfTheCompanyDAOPostgresDAOTest.class);
        Assert.assertNotNull(productDAO);
        Assert.assertNotNull(categoryDAO);
        Assert.assertNotNull(productAttributesDAO);
        Assert.assertNotNull(companyDAO);
        Assert.assertNotNull(productOfTheCompanyDAO);
    }

    //@org.junit.Ignore
    @Override
    @Test
    public void test01SaveAndRetrieveProductOfTheCompany() {
    	super.test01SaveAndRetrieveProductOfTheCompany();
    	
    	LOG.info("Teste test01SaveCompanyInCompanyDAO (DB Postgres) OK!");
    }
    
}
