package br.com.rubyit.resseler.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.ProductAttribute;
import br.com.rubyit.resseler.commons.core.ProductAttributes;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.ProductAttributesEntity;

@Component
public class ProductAttributesDAO extends BaseDAO {
	
	private static final Logger LOG = LogManager.getLogger(ProductAttributesDAO.class);
    @PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
    private EntityManager emResseler;
    
	private String preguica1 = "Falta implementar o method de ";
	private String preguica2 = ". Vai implementar agora?";
	
	public ProductAttributesDAO() {
		
	}
	
	@Transactional
	public ProductAttributesEntity save(ProductAttributes productAtributes) {
		return persist(productAtributes);
	}
	
	@Transactional
	public Boolean saveProductAttributes(ProductAttributes productAtributes) {
		return (persist(productAtributes) == null) ? false : true;
	}

	private ProductAttributesEntity persist(ProductAttributes productAtributes) {
		ProductAttributesEntity eProductAtributes = new ProductAttributesEntity(productAtributes);
		
		LOG.info("persist atributes[" + productAtributes + "]");
		emResseler.persist(eProductAtributes);
		
		return eProductAtributes;
	}
	
	public ProductAttribute retrieveBy(String attributeKey) {
		throw new NotImplementedException(preguica1 + "Busca" + preguica2);
		//aqui eu vou buscar o baton Avon		
	}
	public ProductAttributesEntity retrieveByID(final Long productAttributeID) {
		TypedQuery<ProductAttributesEntity> query = emResseler.createQuery("select T from ProductAttributesEntity T where T.ID = :productAttributeID", ProductAttributesEntity.class);
		query.setParameter("productAttributeID", productAttributeID);
		
		final ProductAttributesEntity result = query.getSingleResult();
		
		return result;
	}

	public ProductAttributesEntity retrieveByeID(Long ID) {
		ProductAttributesEntity entity = emResseler.find(ProductAttributesEntity.class, ID);
		
		return entity;
	}

}
