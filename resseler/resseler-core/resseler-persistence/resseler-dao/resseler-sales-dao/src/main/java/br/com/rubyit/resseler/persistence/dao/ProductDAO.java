package br.com.rubyit.resseler.persistence.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.Price;
import br.com.rubyit.resseler.commons.core.ProductAttributes;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.ModelBrandEntity;
import br.com.rubyit.resseler.persistence.entity.PriceEntity;
import br.com.rubyit.resseler.persistence.entity.ProductAttributesEntity;
import br.com.rubyit.resseler.persistence.entity.ProductEntity;

/* TODO: essa classe precisa de uma refatoracao. Todas as logicas atualmente
 * presentes nesta classe, precisam ser extraidas para o projeto
 * audit-trail-business. */
@Component
public class ProductDAO extends BaseDAO {
	
	private static final Logger LOG = LogManager.getLogger(ProductDAO.class);
    @PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
    private EntityManager emResseler = null;
    @Autowired
    private ProductAttributesDAO productAttributesDAO = null;
    @Autowired
    private PriceDAO priceDAO = null;
    @Autowired
    private ModelBrandDAO modelDAO = null;
	
	public ProductDAO() {
		
	}
	
	@Transactional
	public ProductEntity save(ProductDTO product) {
		return persist(product);
	}
	
	@Transactional
	public Boolean saveProduct(ProductDTO product) {
		return (persist(product) == null) ? false : true;
	}

	@Transactional
	private ProductEntity persist(ProductDTO product) {
		ProductEntity eProductDTO = instanceMapperFactoryAndFacade().map(product, ProductEntity.class);
		
		final ModelBrand productModel = (product == null) ? null : product.getModelBrand();
		if (productModel != null) {
			final ModelBrandEntity eModel = modelDAO.save(productModel);
			eProductDTO.setModelBrand(eModel);
		}
		
		final Price productPrice = (product == null) ? null : product.getProductPrice();
		if (productPrice != null) {
			final PriceEntity ePrice = priceDAO.save(productPrice);
			eProductDTO.setProductPrice(ePrice);
		}
		
		final ProductAttributes productAttributes = (product == null) ? null : product.getProductAttributes();
		if (productAttributes != null) {
			ProductAttributesEntity attributesEntity = productAttributesDAO.save(productAttributes);
			eProductDTO.setProductAttributes(attributesEntity);
		}
		
		LOG.info("persist product[" + eProductDTO + "]");
		
		return save(eProductDTO);
	}
	
	@Transactional
	public ProductEntity save(ProductEntity ProductEntity) {
		emResseler.persist(ProductEntity);
		
		return ProductEntity;
	}
	
	@Transactional(readOnly = true)
	public ProductDTO retrieveBy(final Long productID) {
		final ProductEntity eProduct = retrieveByID(productID);
		final ProductDTO product = fillProductWithEntity(eProduct);
		
		return product;
	}

	public ProductDTO fillProductWithEntity(final ProductEntity eProduct) {
		final ProductDTO product = instanceMapperFactoryAndFacade().map(eProduct, ProductDTO.class);
		if (eProduct != null) {
			product.appendProductDetailDescription(eProduct.getProductDetailDescription());
			final ProductAttributes pAttributes = new ProductAttributes();
			final Map<String, Object> attributes = (eProduct.getProductAttributes() == null) ? null : eProduct.getProductAttributes().getAttributes();
			if (attributes != null) {
				pAttributes.retrieveAttributes().putAll(attributes);
				product.setProductAttributes(pAttributes);
			}
		}
		return product;
	}
	
	@Transactional(readOnly = true)
	public ProductEntity retrieveBy(final ProductDTO product) {
		final String productName = (product == null) ? null : product.getName();
		
		return retrieveBy(productName);
	}

	@Transactional(readOnly = true)
	public ProductEntity retrieveBy(final String productName) {
		TypedQuery<ProductEntity> query = emResseler.createQuery("select T from ProductEntity T where T.name = :productName", ProductEntity.class);
		query.setParameter("productName", productName);
		
		final ProductEntity result = query.getSingleResult();
		
		return result;
	}
	
	public ProductEntity retrieveByEID(final Long eID) {
		final ProductEntity result = emResseler.find(ProductEntity.class, eID);
		
		return result;
	}
	
	public ProductEntity retrieveByID(final Long productID) {
		TypedQuery<ProductEntity> query = emResseler.createQuery("select T from ProductEntity T where T.ID = :productID", ProductEntity.class);
		query.setParameter("productID", productID);
		
		final ProductEntity result = query.getSingleResult();
		
		return result;
	}

}
