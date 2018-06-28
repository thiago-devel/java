package br.com.rubyit.resseler.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.CategoryEntity;

@Component
public class CategoryDAO extends BaseDAO {
	
	private static final Logger LOG = LogManager.getLogger(CategoryDAO.class);
    @PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
    private EntityManager emResseler;
	
	public CategoryDAO() {
		
	}
	
	@Transactional
	public CategoryEntity save(Category category) {
		return persist(category);
	}
	
	@Transactional
	public Boolean saveCategory(Category category) {
		return (persist(category) == null) ? false : true;
	}

	@Transactional
	private CategoryEntity persist(Category category) {
		CategoryEntity eCategory = instanceMapperFactoryAndFacade().map(category, CategoryEntity.class);
		
		LOG.info("persist category[" + category + "]");
		
		return save(eCategory);
	}

	public CategoryEntity save(CategoryEntity categoryEntity) {
		emResseler.persist(categoryEntity);
		
		return categoryEntity;
	}
	
	@Transactional(readOnly = true)
	public Category retrieveBy(final Long categoryID) {
		final CategoryEntity eCategory = retrieveByID(categoryID);
		final Category category = instanceMapperFactoryAndFacade().map(eCategory, Category.class);
		
		return category;
	}
	
	@Transactional(readOnly = true)
	public CategoryEntity retrieveBy(final Category category) {
		final String categoryName = (category == null) ? null : category.getName();
		
		return retrieveBy(categoryName);
	}

	@Transactional(readOnly = true)
	public CategoryEntity retrieveBy(final String categoryName) {
		TypedQuery<CategoryEntity> query = emResseler.createQuery("select T from CategoryEntity T where T.name = :categoryName", CategoryEntity.class);
		query.setParameter("categoryName", categoryName);
		
		CategoryEntity result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			LOG.warn("Category [" + categoryName + "] not found!");
		}
		
		return result;
	}
	
	public CategoryEntity retrieveByID(final Long categoryID) {
		TypedQuery<CategoryEntity> query = emResseler.createQuery("select T from CategoryEntity T where T.ID = :categoryID", CategoryEntity.class);
		query.setParameter("categoryID", categoryID);
		
		final CategoryEntity result = query.getSingleResult();
		
		return result;
	}
	
	public CategoryEntity retrieveByeID(final Long categoryID) {
		final CategoryEntity result = emResseler.find(CategoryEntity.class, categoryID);
		
		return result;
	}

}
