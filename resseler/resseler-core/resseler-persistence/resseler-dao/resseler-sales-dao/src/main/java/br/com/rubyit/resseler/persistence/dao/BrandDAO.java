package br.com.rubyit.resseler.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.BrandEntity;

@Component
public class BrandDAO extends BaseDAO {
	
	private static final Logger LOG = LogManager.getLogger(BrandDAO.class);
    @PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
    private EntityManager emResseler;
	
	public BrandDAO() {
		
	}
	
	@Transactional
	public BrandEntity save(final Brand brand) {
		return persist(brand);
	}
	
	@Transactional
	public Boolean saveBrand(final Brand brand) {
		return (persist(brand) == null) ? false : true;
	}

	@Transactional
	private BrandEntity persist(final Brand brand) {
		BrandEntity eBrand = instanceMapperFactoryAndFacade().map(brand, BrandEntity.class);
		
		LOG.info("persist brand[" + brand + "]");
		final BrandEntity result = save(eBrand);
		
		return result;
	}

	public BrandEntity save(final BrandEntity eBrand) {
		emResseler.persist(eBrand);

		return eBrand;
	}
	
	@Transactional(readOnly = true)
	public Brand retrieveBy(final Long brandID) {
		final BrandEntity eBrand = retrieveByID(brandID);
		final Brand brand = instanceMapperFactoryAndFacade().map(eBrand, Brand.class);
		
		return brand;
	}
	
	@Transactional(readOnly = true)
	public BrandEntity retrieveBy(final Brand brand) {
		final String brandName = (brand == null) ? null : brand.getName();
		
		return retrieveBy(brandName);
	}

	@Transactional(readOnly = true)
	public BrandEntity retrieveBy(final String brandName) {
		TypedQuery<BrandEntity> query = emResseler.createQuery("select T from BrandEntity T where T.name = :brandName", BrandEntity.class);
		query.setParameter("brandName", brandName);
		
		final BrandEntity result = query.getSingleResult();
		
		return result;
	}
	
	public BrandEntity retrieveByID(final Long brandID) {
		TypedQuery<BrandEntity> query = emResseler.createQuery("select T from BrandEntity T where T.ID = :brandID", BrandEntity.class);
		query.setParameter("brandID", brandID);
		
		final BrandEntity result = query.getSingleResult();
		
		return result;
	}
	
	public BrandEntity retrieveByeID(final Long brandID) {
		final BrandEntity result = emResseler.find(BrandEntity.class, brandID);
		
		return result;
	}
}
