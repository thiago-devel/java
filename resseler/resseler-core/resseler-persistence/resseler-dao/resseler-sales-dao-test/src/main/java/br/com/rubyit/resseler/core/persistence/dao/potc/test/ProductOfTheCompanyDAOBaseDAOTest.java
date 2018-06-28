package br.com.rubyit.resseler.core.persistence.dao.potc.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.ProductOfTheCompany;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.persistence.dao.test.BaseProductOfTheCompanyTest;
import br.com.rubyit.resseler.persistence.entity.ProductOfTheCompanyEntity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class ProductOfTheCompanyDAOBaseDAOTest extends BaseProductOfTheCompanyTest {
    protected static Logger LOG = null;

    @Test
    public void test01SaveAndRetrieveProductOfTheCompany() {
    	
    	ModelBrand modeloBatomAvon = createTestModelBrand();
    	
    	ProductDTO batonQualquer = createTestProductBatomQualquer(modeloBatomAvon);
    	
    	Category categoriaProdutosBatom = createTestProductCategoryTree();
    	
    	Company companyAvon = createTestCompany();
    	
    	Brand brandAvon = createTestBrand();
    	
    	ProductOfTheCompany productOfTheCompany = new ProductOfTheCompany();
    	productOfTheCompany.setProduct(batonQualquer);
    	productOfTheCompany.setCategory(categoriaProdutosBatom);
    	productOfTheCompany.setBrand(brandAvon);
    	productOfTheCompany.setCompany(companyAvon);
    	
    	ProductOfTheCompanyEntity eProductOfTheCompany = productOfTheCompanyDAO.save(batonQualquer, categoriaProdutosBatom, brandAvon, companyAvon);
    	
    	Assert.assertEquals(batonQualquer, productOfTheCompanyDAO.retrieveProductByProductID(batonQualquer));
    	Assert.assertEquals(categoriaProdutosBatom, productOfTheCompanyDAO.retrieveCategoryByProductID(batonQualquer));
    	Assert.assertEquals(brandAvon, productOfTheCompanyDAO.retrieveBrandByProductID(batonQualquer));
    	Assert.assertEquals(companyAvon, productOfTheCompanyDAO.retrieveCompanyByProductID(batonQualquer));
    	
    	Assert.assertEquals(batonQualquer, productOfTheCompanyDAO.retrieveProductByCategoryID(categoriaProdutosBatom));
    	Assert.assertEquals(categoriaProdutosBatom, productOfTheCompanyDAO.retrieveCategoryByCategoryID(categoriaProdutosBatom));
    	Assert.assertEquals(brandAvon, productOfTheCompanyDAO.retrieveBrandByCategoryID(categoriaProdutosBatom));
    	Assert.assertEquals(companyAvon, productOfTheCompanyDAO.retrieveCompanyByCategoryID(categoriaProdutosBatom));
    	
    	Assert.assertEquals(batonQualquer, productOfTheCompanyDAO.retrieveProductByBrandID(brandAvon));
    	Assert.assertEquals(categoriaProdutosBatom, productOfTheCompanyDAO.retrieveCategoryByBrandID(brandAvon));
    	Assert.assertEquals(brandAvon, productOfTheCompanyDAO.retrieveBrandByBrandID(brandAvon));
    	Assert.assertEquals(companyAvon, productOfTheCompanyDAO.retrieveCompanyByBrandID(brandAvon));
    	
    	Assert.assertEquals(batonQualquer, productOfTheCompanyDAO.retrieveProductByCompanyID(companyAvon));
    	Assert.assertEquals(categoriaProdutosBatom, productOfTheCompanyDAO.retrieveCategoryByCompanyID(companyAvon));
    	Assert.assertEquals(brandAvon, productOfTheCompanyDAO.retrieveBrandByCompanyID(companyAvon));
    	Assert.assertEquals(companyAvon, productOfTheCompanyDAO.retrieveCompanyByCompanyID(companyAvon));
    	
    	ProductOfTheCompany result = productOfTheCompanyDAO.retrievePojo(eProductOfTheCompany);
    	
    	Assert.assertEquals(productOfTheCompany, result);
    	
    }
    
    @Test
    public void test02SaveListOfProductsOfTheCompany() {
    	ModelBrand modeloBatomAvon = createTestModelBrand();
    	ModelBrand modeloBatomIntense = createTestModelBrand01();
    	
    	ProductDTO batonAvon = createTestProductBatonAvon(modeloBatomAvon);
    	ProductDTO batonBoticarioIntense = createTestProductBatomBoticarioIntense(modeloBatomIntense);
    	
    	Category categoriaProdutosBatom = createTestProductCategoryTree();
    	
    	Company companyAvon = createTestCompany();
    	Company companyBoticario = createTestCompanyBoticario();
    	
    	Brand brandAvon = createTestBrand();
    	Brand brandBatonsBoticario = createTestBrand01();
    	
    	ProductOfTheCompany productOfTheCompanyAvon = new ProductOfTheCompany();
    	productOfTheCompanyAvon.setProduct(batonAvon);
    	productOfTheCompanyAvon.setCategory(categoriaProdutosBatom);
    	productOfTheCompanyAvon.setBrand(brandAvon);
    	productOfTheCompanyAvon.setCompany(companyAvon);
    	
    	ProductOfTheCompany productOfTheCompanyBoticario = new ProductOfTheCompany();
    	productOfTheCompanyBoticario.setProduct(batonBoticarioIntense);
    	productOfTheCompanyBoticario.setCategory(categoriaProdutosBatom);
    	productOfTheCompanyBoticario.setBrand(brandBatonsBoticario);
    	productOfTheCompanyBoticario.setCompany(companyBoticario);
    	
    	List<ProductOfTheCompany> products = new ArrayList<>();
    	products.add(productOfTheCompanyAvon);
    	products.add(productOfTheCompanyBoticario);
    	
    	productOfTheCompanyDAO.save(products);
    	
    	Assert.assertEquals(batonAvon, productOfTheCompanyDAO.retrieveProductByProductID(batonAvon));
    	Assert.assertEquals(categoriaProdutosBatom, productOfTheCompanyDAO.retrieveCategoryByProductID(batonAvon));
    	Assert.assertEquals(brandAvon, productOfTheCompanyDAO.retrieveBrandByProductID(batonAvon));
    	Assert.assertEquals(companyAvon, productOfTheCompanyDAO.retrieveCompanyByProductID(batonAvon));
    	
    	Assert.assertEquals(batonBoticarioIntense, productOfTheCompanyDAO.retrieveProductByProductID(batonBoticarioIntense));
    	Assert.assertEquals(categoriaProdutosBatom, productOfTheCompanyDAO.retrieveCategoryByProductID(batonBoticarioIntense));
    	Assert.assertEquals(brandBatonsBoticario, productOfTheCompanyDAO.retrieveBrandByProductID(batonBoticarioIntense));
    	Assert.assertEquals(companyBoticario, productOfTheCompanyDAO.retrieveCompanyByProductID(batonBoticarioIntense));
    }
    
}
