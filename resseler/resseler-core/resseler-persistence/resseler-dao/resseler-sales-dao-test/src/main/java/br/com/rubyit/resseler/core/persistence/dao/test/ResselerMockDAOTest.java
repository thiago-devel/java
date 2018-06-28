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

import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.ProductAttributes;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.persistence.config.dao.test.TestMockDAOAppConfig;
import br.com.rubyit.resseler.persistence.entity.ProductAttributesEntity;

// @org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestMockDAOAppConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResselerMockDAOTest extends BaseProductOfTheCompanyTest {

    private static final Logger LOG = LogManager.getLogger(ResselerMockDAOTest.class);
    
    @Before
    public void setup() {
        Assert.assertNotNull(productDAO);
        Assert.assertNotNull(categoryDAO);
        Assert.assertNotNull(productAttributesDAO);
    }

    @Test
    public void test01SaveInProductDAO() {
    	ModelBrand modeloBatomAvon = createTestModelBrand();
    	
    	ProductDTO batonAvon = createTestProductBatonAvon(modeloBatomAvon);
    	
    	Assert.assertTrue(productDAO.saveProduct(batonAvon));
    	
    	LOG.info("Teste test01SaveInProductDAO OK!");
    }
    
    @Test
    public void test02PersistAndRetrieveProductAttributes() {
    	ProductAttributes attributosDoBatomAvon = createTestAttributes();
    	
    	ProductAttributesEntity attrEntity = productAttributesDAO.save(attributosDoBatomAvon);
    	Assert.assertNotNull(attrEntity);
    	Long ID = attrEntity.getID();
    	
    	ProductAttributesEntity attr2 = productAttributesDAO.retrieveByID(ID);
    	Assert.assertNotNull(attr2);
    	
    	LOG.info("Teste test02PersistAndRetrieveProductAttributes OK!");
    }

}
