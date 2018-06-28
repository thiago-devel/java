package br.com.rubyit.resseler.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.ModelBrandEntity;

@Component
public class ModelBrandDAO extends BaseDAO {
	
	private static final Logger LOG = LogManager.getLogger(ModelBrandDAO.class);
    @PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
    private EntityManager emResseler;
	
	public ModelBrandDAO() {
		
	}
	
	@Transactional
	public ModelBrandEntity save(final ModelBrand model) {
		return persist(model);
	}
	
	@Transactional
	public Boolean saveBrand(final ModelBrand model) {
		return (persist(model) == null) ? false : true;
	}

	private ModelBrandEntity persist(final ModelBrand model) {
		ModelBrandEntity eModel = instanceMapperFactoryAndFacade().map(model, ModelBrandEntity.class);
		
		LOG.info("persist model[" + model + "]");
		final ModelBrandEntity result = save(eModel);
		
		return result;
	}

	public ModelBrandEntity save(final ModelBrandEntity eModel) {
		emResseler.persist(eModel);

		return eModel;
	}
	
	@Transactional(readOnly = true)
	public ModelBrand retrieveBy(final Long brandID) {
		final ModelBrandEntity eModel = retrieveByID(brandID);
		final ModelBrand model = instanceMapperFactoryAndFacade().map(eModel, ModelBrand.class);
		
		return model;
	}
	
	@Transactional(readOnly = true)
	public ModelBrandEntity retrieveBy(final ModelBrand model) {
		final String modelName = (model == null) ? null : model.getName();
		
		return retrieveBy(modelName);
	}

	@Transactional(readOnly = true)
	public ModelBrandEntity retrieveBy(final String modelName) {
		TypedQuery<ModelBrandEntity> query = emResseler.createQuery("select T from BrandEntity T where T.name = :modelName", ModelBrandEntity.class);
		query.setParameter("modelName", modelName);
		
		final ModelBrandEntity result = query.getSingleResult();
		
		return result;
	}
	
	public ModelBrandEntity retrieveByID(final Long modelID) {
		TypedQuery<ModelBrandEntity> query = emResseler.createQuery("select T from BrandEntity T where T.ID = :modelID", ModelBrandEntity.class);
		query.setParameter("modelID", modelID);
		
		final ModelBrandEntity result = query.getSingleResult();
		
		return result;
	}
	
	public ModelBrandEntity retrieveByeID(final Long brandID) {
		final ModelBrandEntity result = emResseler.find(ModelBrandEntity.class, brandID);
		
		return result;
	}
}
