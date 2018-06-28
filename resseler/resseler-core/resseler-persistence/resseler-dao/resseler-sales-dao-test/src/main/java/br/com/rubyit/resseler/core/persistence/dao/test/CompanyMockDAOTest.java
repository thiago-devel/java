package br.com.rubyit.resseler.core.persistence.dao.test;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.id.serial.LongGenerator;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.Price;
import br.com.rubyit.resseler.commons.core.ProductOfTheCompany;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.persistence.config.dao.test.TestMockDAOAppConfig;
import br.com.rubyit.resseler.persistence.dao.CategoryDAO;
import br.com.rubyit.resseler.persistence.dao.CompanyDAO;
import br.com.rubyit.resseler.persistence.dao.ProductAttributesDAO;
import br.com.rubyit.resseler.persistence.dao.ProductDAO;
import br.com.rubyit.resseler.persistence.dao.ProductOfTheCompanyDAO;
import br.com.rubyit.resseler.persistence.entity.CompanyEntity;

// @org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestMockDAOAppConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyMockDAOTest extends BaseProductOfTheCompanyTest {

    private static final Logger LOG = LogManager.getLogger(CompanyMockDAOTest.class);

    
    @Before
    public void setup() {
        Assert.assertNotNull(productDAO);
        Assert.assertNotNull(categoryDAO);
        Assert.assertNotNull(productAttributesDAO);
        Assert.assertNotNull(companyDAO);
        Assert.assertNotNull(productOfTheCompanyDAO);
    }

    //@org.junit.Ignore
    @Test
    public void test01SaveCompanyInCompanyDAO() {
    	ProductOfTheCompany productOfTheCompany = new ProductOfTheCompany();
    	
    	ModelBrand modeloBatomAvon = createTestModelBrand();
    	
    	ProductDTO batonAvon = createTestProductBatonAvon(modeloBatomAvon);
    	
    	Category categoriaProdutosBatom = createTestProductCategoryTree();
    	
    	Company companyAvon = createTestCompany();
    	
    	Brand brandAvon = createTestBrand();
    	
    	Price batonAvonPrice = new Price();
    	batonAvonPrice.putArbitraryPrice(BigDecimal.valueOf(21,99));
    	
    	productOfTheCompany.setCompany(companyAvon);
    	productOfTheCompany.setBrand(brandAvon);
    	productOfTheCompany.setCategory(categoriaProdutosBatom);
    	productOfTheCompany.setProduct(batonAvon);
    	
    	productOfTheCompanyDAO.save(productOfTheCompany);
    	
    	Company retrivedCompay = productOfTheCompanyDAO.retrieveCompanyByCompanyID(companyAvon);
    	Assert.assertNotNull(retrivedCompay);
    	Assert.assertEquals(companyAvon, retrivedCompay);
    	
    	List<Brand> brands = productOfTheCompanyDAO.retrieveBrandsByCompanyID(companyAvon);
    	Assert.assertNotNull(brands);
    	Assert.assertEquals(1, brands.size());
    	Assert.assertEquals(brandAvon, brands.get(0));
    	
    	List<ProductDTO> products = productOfTheCompanyDAO.retrieveProductsByCompanyID(companyAvon);
    	Assert.assertNotNull(products);
    	Assert.assertEquals(1, products.size());
    	Assert.assertEquals(batonAvon, products.get(0));
    	
    	List<Category> categories = productOfTheCompanyDAO.retrieveCategoriesByCompanyID(companyAvon);
    	Assert.assertNotNull(categories);
    	Assert.assertEquals(1, categories.size());
    	Assert.assertEquals(categoriaProdutosBatom, categories.get(0));

    	LOG.info("Teste test01SaveCompanyInCompanyDAO OK!");
    }    

}
