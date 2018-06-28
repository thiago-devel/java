package br.com.rubyit.resseler.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.Price;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.PriceEntity;

@Component
public class PriceDAO extends BaseDAO {
	
	private static final Logger LOG = LogManager.getLogger(PriceDAO.class);
    @PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
    private EntityManager emResseler;
	
	public PriceDAO() {
		
	}
	
	@Transactional
	public PriceEntity save(Price price) {
		return persist(price);
	}
	
	@Transactional
	public Boolean savePrice(Price price) {
		return (persist(price) == null) ? false : true;
	}

	private PriceEntity persist(Price price) {
		PriceEntity ePrice = instanceMapperFactoryAndFacade().map(price, PriceEntity.class);
		
		LOG.info("persist price[" + price + "]");
		emResseler.persist(ePrice);
		
		return ePrice;
	}

	public PriceEntity retrieveBy(Long ID) {
		PriceEntity entity = emResseler.find(PriceEntity.class, ID);
		
		return entity;
	}

}
