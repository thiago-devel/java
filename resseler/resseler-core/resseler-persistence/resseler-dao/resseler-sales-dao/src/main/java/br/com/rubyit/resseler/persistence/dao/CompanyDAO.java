package br.com.rubyit.resseler.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;
import br.com.rubyit.resseler.persistence.entity.CompanyEntity;

@Component
public class CompanyDAO extends BaseDAO {

	private static final Logger LOG = LogManager.getLogger(CompanyDAO.class);
	@PersistenceContext(unitName = DAOResselerAppConfig.PERSISTENCE_UNIT_NAME_RESSELER)
	private EntityManager emResseler;

	public CompanyDAO() {

	}

	@Transactional
	public CompanyEntity save(final Company company) {
		return persist(company);
	}

	@Transactional
	public boolean saveCompany(final Company company) {
		return (persist(company) == null) ? false : true;
	}

	@Transactional
	private CompanyEntity persist(final Company company) {
		final CompanyEntity eCompany = instanceMapperFactoryAndFacade().map(company,  CompanyEntity.class);
		
		LOG.info("persist company[" + company + "]");
		emResseler.persist(eCompany);

		return eCompany;
	}
	
	@Transactional(readOnly = true)
	public Company retrieveBy(final Long companyID) {
		final CompanyEntity eCompany = retrieveByID(companyID);
		final Company Company = instanceMapperFactoryAndFacade().map(eCompany, Company.class);
		
		return Company;
	}
	
	@Transactional(readOnly = true)
	public CompanyEntity retrieveBy(final Company company) {
		final String CompanyName = (company == null) ? null : company.getName();
		
		return retrieveBy(CompanyName);
	}

	@Transactional(readOnly = true)
	public CompanyEntity retrieveBy(final String companyName) {
		TypedQuery<CompanyEntity> query = emResseler.createQuery("select T from CompanyEntity T where T.name = :companyName", CompanyEntity.class);
		query.setParameter("companyName", companyName);
		
		final CompanyEntity result = query.getSingleResult();
		
		return result;
	}
	
	public CompanyEntity retrieveByID(final Long companyID) {
		TypedQuery<CompanyEntity> query = emResseler.createQuery("select T from CompanyEntity T where T.ID = :companyID", CompanyEntity.class);
		query.setParameter("companyID", companyID);
		
		final CompanyEntity result = query.getSingleResult();
		
		return result;
	}
	
	public CompanyEntity retrieveByeID(final Long companyID) {
		final CompanyEntity result = emResseler.find(CompanyEntity.class, companyID);
		
		return result;
	}
}
