package br.com.rubyit.resseler.core.persistence.dao.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.core.persistence.config.dao.test.TestMockDAOAppConfig;

// @org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestMockDAOAppConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BrandMockDAOTest extends BaseProductOfTheCompanyTest {

    private static final Logger LOG = LogManager.getLogger(BrandMockDAOTest.class);
    
    @Before
    public void setup() {
        Assert.assertNotNull(productDAO);
        Assert.assertNotNull(categoryDAO);
        Assert.assertNotNull(productAttributesDAO);
        Assert.assertNotNull(brandDAO);
        Assert.assertNotNull(companyDAO);
    }

    @Test
    public void test01SaveBrandInBrandDAO() {
    	final Brand brandAvon = createTestBrand();
    	
    	Assert.assertTrue(brandDAO.saveBrand(brandAvon));
    	
    	LOG.info("Teste test01SaveBrandInBrandDAO OK!");
    }    

}
