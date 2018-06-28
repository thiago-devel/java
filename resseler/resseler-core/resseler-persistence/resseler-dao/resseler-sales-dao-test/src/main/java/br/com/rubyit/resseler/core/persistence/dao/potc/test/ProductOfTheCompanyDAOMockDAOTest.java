package br.com.rubyit.resseler.core.persistence.dao.potc.test;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rubyit.resseler.core.persistence.config.dao.test.TestMockDAOAppConfig;

// @org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestMockDAOAppConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductOfTheCompanyDAOMockDAOTest extends ProductOfTheCompanyDAOBaseDAOTest {
    
    @Before
    public void setup() {
    	LOG = LogManager.getLogger(ProductOfTheCompanyDAOMockDAOTest.class);
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
    	
    	LOG.info("Teste test01SaveCompanyInCompanyDAO (DB HSQLDB) OK!");
    }
    
    @Override
    @Test
    public void test02SaveListOfProductsOfTheCompany() {
    	super.test02SaveListOfProductsOfTheCompany();
    	
    	LOG.info("Teste test02SaveListOfProductsOfTheCompany (DB HSQLDB) OK!");
    }	
}
