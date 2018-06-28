package br.com.rubyit.resseler.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.ProductOfTheCompany;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.BrandEntity;
import br.com.rubyit.resseler.persistence.entity.CategoryEntity;
import br.com.rubyit.resseler.persistence.entity.CompanyEntity;
import br.com.rubyit.resseler.persistence.entity.ProductEntity;
import br.com.rubyit.resseler.persistence.entity.ProductOfTheCompanyEntity;

@Component
public class ProductOfTheCompanyDAO extends BaseDAO {

	private static final Logger LOG = LogManager.getLogger(ProductOfTheCompanyDAO.class);
    @PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
    private EntityManager emResseler;
    @Autowired
    protected ProductDAO productDAO;
    @Autowired
    protected CategoryDAO categoryDAO;
    @Autowired
    protected BrandDAO brandDAO;
    @Autowired
    protected CompanyDAO companyDAO;
    
    public ProductOfTheCompanyDAO() {
    	
    }
    
    @Transactional
	public ProductOfTheCompanyEntity save(final ProductDTO product, final Category category, final Brand brand, final Company company) {
		ProductOfTheCompany productOfTheCompany = new ProductOfTheCompany();
		productOfTheCompany.setProduct(product);
		productOfTheCompany.setCategory(category);
		productOfTheCompany.setBrand(brand);
		productOfTheCompany.setCompany(company);
		
		return save(productOfTheCompany);
	}
    
    @Transactional
    public ProductOfTheCompanyEntity save(final ProductOfTheCompany productOfTheCompany) {
    	return persist(productOfTheCompany);
    }
	
    @Transactional
	public Boolean saveProductOfTheCompany(final ProductOfTheCompany productOfTheCompany) {
		return (persist(productOfTheCompany) == null) ? false : true;
	}
    
	@Transactional
    public Boolean save(List<ProductOfTheCompany> products) {
    	boolean result = false;
    	for (ProductOfTheCompany productOfTheCompany : products) {
			result = (saveProductOfTheCompany(productOfTheCompany) == null) ? false : true;
		}
    	
    	return result;
    }
	
    @Transactional
	private ProductOfTheCompanyEntity persist(final ProductOfTheCompany productOfTheCompany) {
		final ProductDTO product = (productOfTheCompany == null) ? null : productOfTheCompany.getProduct();
		final ProductEntity eProduct = productDAO.save(product);
		
		final Category category = (productOfTheCompany == null) ? null : productOfTheCompany.getCategory();
		CategoryEntity eCategory = categoryDAO.retrieveBy(category);
		eCategory = (eCategory == null) ? categoryDAO.save(category) : eCategory;
		
		final Brand brand = (productOfTheCompany == null) ? null : productOfTheCompany.getBrand();
		final BrandEntity eBrand = brandDAO.save(brand);
		
		final Company company = (productOfTheCompany == null) ? null : productOfTheCompany.getCompany();
		final CompanyEntity eCompany = companyDAO.save(company);
		
		ProductOfTheCompanyEntity eProductOfTheCompany = new ProductOfTheCompanyEntity();
		eProductOfTheCompany.setProduct(eProduct);
		eProductOfTheCompany.setCategory(eCategory);
		eProductOfTheCompany.setBrand(eBrand);
		eProductOfTheCompany.setCompany(eCompany);
		
		emResseler.persist(eProductOfTheCompany);
		
		return eProductOfTheCompany;
	}
	
	@Transactional(readOnly = true)
	public ProductDTO retrieveProductByProductID(final ProductDTO product) {
		final Long productID = (product == null) ? null : product.getID();
		final ProductDTO result = productDAO.retrieveBy(productID);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Category retrieveCategoryByProductID(final ProductDTO product) {
		final Long productID = (product == null) ? null : product.getID();
		final ProductEntity eProduct = productDAO.retrieveByID(productID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eProduct);
		final CategoryEntity eCategory = productOfTheCompanyEntity.getCategory();
		final Category result = instanceMapperFactoryAndFacade().map(eCategory, Category.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Brand retrieveBrandByProductID(final ProductDTO product) {
		final Long productID = (product == null) ? null : product.getID();
		final ProductEntity eProduct = productDAO.retrieveByID(productID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eProduct);
		final BrandEntity eBrand = productOfTheCompanyEntity.getBrand();
		final Brand result = instanceMapperFactoryAndFacade().map(eBrand, Brand.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Company retrieveCompanyByProductID(final ProductDTO product) {
		final Long productID = (product == null) ? null : product.getID();
		final ProductEntity eProduct = productDAO.retrieveByID(productID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eProduct);
		final CompanyEntity eCompany = productOfTheCompanyEntity.getCompany();
		final Company result = instanceMapperFactoryAndFacade().map(eCompany, Company.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public ProductDTO retrieveProductByCategoryID(final Category category) {
		final Long categoryID = (category == null) ? null : category.getID();
		final CategoryEntity eCategory = categoryDAO.retrieveByID(categoryID);
		final List<ProductOfTheCompanyEntity> list = retrieveProductOfCompanyListBy(eCategory);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = (list != null && list.size() > 0) ? list.get(0) : null;
		ProductDTO result = null;
		if (productOfTheCompanyEntity != null) {
			final ProductEntity eProduct = productOfTheCompanyEntity.getProduct();
			result = productDAO.fillProductWithEntity(eProduct);
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Category retrieveCategoryByCategoryID(final Category category) {
		final Long categoryID = (category == null) ? null : category.getID();
		final CategoryEntity eCategory = categoryDAO.retrieveByID(categoryID);
		final Category result = instanceMapperFactoryAndFacade().map(eCategory, Category.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Brand retrieveBrandByCategoryID(final Category category) {
		final Long categoryID = (category == null) ? null : category.getID();
		final CategoryEntity eCategory = categoryDAO.retrieveByID(categoryID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eCategory);
		final BrandEntity eBrand = productOfTheCompanyEntity.getBrand();
		final Brand result = instanceMapperFactoryAndFacade().map(eBrand, Brand.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Company retrieveCompanyByCategoryID(final Category category) {
		final Long categoryID = (category == null) ? null : category.getID();
		final CategoryEntity eCategory = categoryDAO.retrieveByID(categoryID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eCategory);
		final CompanyEntity eCompany = productOfTheCompanyEntity.getCompany();
		final Company result = instanceMapperFactoryAndFacade().map(eCompany, Company.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public ProductDTO retrieveProductByBrandID(final Brand brand) {
		final Long brandID = (brand == null) ? null : brand.getID();
		final BrandEntity eBrand = brandDAO.retrieveByID(brandID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eBrand);
		final ProductEntity eProduct = productOfTheCompanyEntity.getProduct();
		final ProductDTO result = productDAO.fillProductWithEntity(eProduct);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Category retrieveCategoryByBrandID(final Brand brand) {
		final Long brandID = (brand == null) ? null : brand.getID();
		final BrandEntity eBrand = brandDAO.retrieveByID(brandID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eBrand);
		final CategoryEntity eCategory = productOfTheCompanyEntity.getCategory();
		final Category result = instanceMapperFactoryAndFacade().map(eCategory, Category.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Brand retrieveBrandByBrandID(final Brand brand) {
		final Long brandID = (brand == null) ? null : brand.getID();
		final BrandEntity eBrand = brandDAO.retrieveByID(brandID);
		final Brand result = instanceMapperFactoryAndFacade().map(eBrand, Brand.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Company retrieveCompanyByBrandID(final Brand brand) {
		final Long brandID = (brand == null) ? null : brand.getID();
		final BrandEntity eBrand = brandDAO.retrieveByID(brandID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eBrand);
		final CompanyEntity eCompany = productOfTheCompanyEntity.getCompany();
		final Company result = instanceMapperFactoryAndFacade().map(eCompany, Company.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public ProductDTO retrieveProductByCompanyID(final Company company) {
		final Long companyID = (company == null) ? null : company.getID();
		final ProductEntity eProduct = retrieveProductEntityByCompanyID(companyID);
		final ProductDTO result = productDAO.fillProductWithEntity(eProduct);
		
		return result;
	}

	@Transactional(readOnly = true)
	public ProductEntity retrieveProductEntityByCompanyID(final Long companyID) {
		final CompanyEntity eCompany = retrieveCompanyEntityByCompanyID(companyID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eCompany);
		final ProductEntity eProduct = productOfTheCompanyEntity.getProduct();
		return eProduct;
	}
	
	@Transactional(readOnly = true)
	public Category retrieveCategoryByCompanyID(final Company company) {
		final Long companyID = (company == null) ? null : company.getID();
		final CompanyEntity eCompany = retrieveCompanyEntityByCompanyID(companyID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eCompany);
		final CategoryEntity eCategory = productOfTheCompanyEntity.getCategory();
		final Category result = instanceMapperFactoryAndFacade().map(eCategory, Category.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Brand retrieveBrandByCompanyID(final Company company) {
		final Long companyID = (company == null) ? null : company.getID();
		final CompanyEntity eCompany = retrieveCompanyEntityByCompanyID(companyID);
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = retrieveProductOfCompanyBy(eCompany);
		final BrandEntity eBrand = productOfTheCompanyEntity.getBrand();
		final Brand result = instanceMapperFactoryAndFacade().map(eBrand, Brand.class);
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Company retrieveCompanyByCompanyID(final Company company) {
		final Long companyID = (company == null) ? null : company.getID();
		final CompanyEntity eCompany = retrieveCompanyEntityByCompanyID(companyID);
		final Company result = instanceMapperFactoryAndFacade().map(eCompany, Company.class);
		
		return result;
	}

	@Transactional(readOnly = true)
	public CompanyEntity retrieveCompanyEntityByCompanyID(final Long companyID) {
		final CompanyEntity eCompany = companyDAO.retrieveByID(companyID);
		return eCompany;
	}

	public List<ProductDTO> retrieveProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductDTO retrieveProductBy(Brand brand) {
		// TODO Auto-generated method stub
		return null;
	}

	public Brand retrieveBrandBy(ProductDTO product) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//implementar uma busca para cada um dos retornos acima usando cada um tipo de ID de possivel parametro
	
	private ProductOfTheCompanyEntity retrieveProductOfCompanyBy(final ProductEntity eProduct) {
		TypedQuery<ProductOfTheCompanyEntity> query = emResseler.createQuery("select T from ProductOfTheCompanyEntity T where T.product = :product", ProductOfTheCompanyEntity.class);
		query.setParameter("product", eProduct);
		
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = query.getSingleResult();
		
		return productOfTheCompanyEntity;
	}
	
	private ProductOfTheCompanyEntity retrieveProductOfCompanyBy(final CategoryEntity eCatagory) {
		TypedQuery<ProductOfTheCompanyEntity> query = buildQueryProductOfCompanyByCategory(eCatagory);
		
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = query.getSingleResult();
		
		return productOfTheCompanyEntity;
	}
	
	private List<ProductOfTheCompanyEntity> retrieveProductOfCompanyListBy(final CategoryEntity eCatagory) {
		TypedQuery<ProductOfTheCompanyEntity> query = buildQueryProductOfCompanyByCategory(eCatagory);
		
		final List<ProductOfTheCompanyEntity> productOfTheCompanyEntity = query.getResultList();
		
		return productOfTheCompanyEntity;
	}

	private TypedQuery<ProductOfTheCompanyEntity> buildQueryProductOfCompanyByCategory(final CategoryEntity eCatagory) {
		TypedQuery<ProductOfTheCompanyEntity> query = emResseler.createQuery("select T from ProductOfTheCompanyEntity T where T.category = :category", ProductOfTheCompanyEntity.class);
		query.setParameter("category", eCatagory);
		return query;
	}
	
	private ProductOfTheCompanyEntity retrieveProductOfCompanyBy(final BrandEntity eBrand) {
		TypedQuery<ProductOfTheCompanyEntity> query = emResseler.createQuery("select T from ProductOfTheCompanyEntity T where T.brand = :brand", ProductOfTheCompanyEntity.class);
		query.setParameter("brand", eBrand);
		
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = query.getSingleResult();
		
		return productOfTheCompanyEntity;
	}
	
	private ProductOfTheCompanyEntity retrieveProductOfCompanyBy(final CompanyEntity eCompany) {
		TypedQuery<ProductOfTheCompanyEntity> query = emResseler.createQuery("select T from ProductOfTheCompanyEntity T where T.company = :company", ProductOfTheCompanyEntity.class);
		query.setParameter("company", eCompany);
		
		final ProductOfTheCompanyEntity productOfTheCompanyEntity = query.getSingleResult();
		
		return productOfTheCompanyEntity;
	}

	public ProductOfTheCompany retrievePojo(ProductOfTheCompanyEntity eProductOfTheCompany) {
		ProductOfTheCompany result = instanceMapperFactoryAndFacade().map(eProductOfTheCompany, ProductOfTheCompany.class);
    	result.setProduct(productDAO.fillProductWithEntity(eProductOfTheCompany.getProduct()));
    	
		return result;
	}

	public List<Brand> retrieveBrandsByCompanyID(Company company) {
		final Long companyID = (company == null) ? null : company.getID();
		final CompanyEntity eCompany = retrieveCompanyEntityByCompanyID(companyID);
		TypedQuery<BrandEntity> query = emResseler.createQuery("select B from ProductOfTheCompanyEntity T, BrandEntity B where T.company = :company and T.brand = B", BrandEntity.class);
		query.setParameter("company", eCompany);
		
		final List<BrandEntity> eBrands = query.getResultList();
		List<Brand> result = (eBrands == null || eBrands.size() == 0) ? null : new ArrayList<>();
		for (BrandEntity brandEntity : eBrands) {
			final Brand brand = instanceMapperFactoryAndFacade().map(brandEntity, Brand.class);

			result.add(brand);
		}
		
		return result;
	}

	public List<ProductDTO> retrieveProductsByCompanyID(Company company) {
		final Long companyID = (company == null) ? null : company.getID();
		final CompanyEntity eCompany = retrieveCompanyEntityByCompanyID(companyID);
		TypedQuery<ProductEntity> query = emResseler.createQuery("select P from ProductOfTheCompanyEntity T, ProductEntity P where T.company = :company and T.product = P", ProductEntity.class);
		query.setParameter("company", eCompany);
		
		final List<ProductEntity> eProducts = query.getResultList();
		List<ProductDTO> result = (eProducts == null || eProducts.size() == 0) ? null : new ArrayList<>();
		for (ProductEntity productEntity : eProducts) {
			final ProductDTO product = productDAO.fillProductWithEntity(productEntity);

			result.add(product);
		}
		
		return result;
	}

	public List<Category> retrieveCategoriesByCompanyID(Company company) {
		final Long companyID = (company == null) ? null : company.getID();
		final CompanyEntity eCompany = retrieveCompanyEntityByCompanyID(companyID);
		TypedQuery<CategoryEntity> query = emResseler.createQuery("select C from ProductOfTheCompanyEntity T, CategoryEntity C where T.company = :company and T.brand = C", CategoryEntity.class);
		query.setParameter("company", eCompany);
		
		final List<CategoryEntity> eCategories = query.getResultList();
		List<Category> result = (eCategories == null || eCategories.size() == 0) ? null : new ArrayList<>();
		for (CategoryEntity categoryEntity : eCategories) {
			final Category brand = instanceMapperFactoryAndFacade().map(categoryEntity, Category.class);

			result.add(brand);
		}
		
		return result;
	}
}
