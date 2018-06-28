package br.com.resseler.directsales.commons.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO.retriveSystemPersonBy;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BCH_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_ATU_BCH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_BCH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_COD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_SALESMAN;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.baseQueryRetrievePerformance;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveCodRegionForName;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrievePersonBySalesmanID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveRegionAndBranchByBranchID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveSalesmanByPersonID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveSalesmanBySalesmanID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveSalesmanIDByCPF;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveSalesmanPersonIDBySalesmanID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetriveByID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryfindSalesmanBchByBranch;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.updateSalesmanBranch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.resseler.directsales.commons.persistence.dao.rowmappers.RegionIDRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.SalesmanIDRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.SalesmanRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.SalesmanSPIDRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.SalesmanScoreRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.WorkshopCodeRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.WorkshopIDRM;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.commons.dto.Regional;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SalesmanScore;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;
import br.com.rubyit.resseler.core.commons.dto.Workshop;
import br.com.rubyit.resseler.core.commons.exceptions.BusinessException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.enums.ErrorType;

@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
        "squid:S1200" }, justification = "Nivel minimo de acoplamento ja foi atingido. O alto numero de "
                + "imports de classes se deve ao fato de se tratar de uma classe "
                + "se configuracao do Spring.")
public class SalesmanDAO {

    private static final Logger LOG = LogManager.getLogger(SalesmanDAO.class);

    @Autowired(required = true)
    private javax.sql.DataSource dataSourceDirectSales;

    /**
     * Default constructor
     */
    public SalesmanDAO() {
        // do nothing
    }

    public SalesmanDAO(final javax.sql.DataSource dataSource) {
        dataSourceDirectSales = dataSource;
    }

    public Long persist(final Salesman salesman) throws DataAccessException{

        if (salesman == null) {
            final String msg = "ERROR: can not persist a null Salesman!";
            LOG.error(msg);
            throw new IllegalArgumentException(msg);
        }

        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        final Map<String, Object> args = new HashMap<>();
        args.put(PERSON_ID, salesman.getPersonId());
        args.put(SALESMAN_COD, salesman.getSalesmanCod());
        args.put(SALESMAN_BCH, salesman.getSalesmanBch());
        args.put(SALESMAN_ATU_BCH, salesman.getSalesmanAtuBch());
        args.put(BCH_ID, salesman.getBchId());

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_SALESMAN)
                .usingGeneratedKeyColumns(SALESMAN_ID);
        final Number key = insert.executeAndReturnKey(args);

        if (key == null) {
            LOG.error("Fail to persist Salesman[" + salesman + "] to "
                    + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        LOG.debug("Salesman[" + salesman + "] saved to "
                + PERSISTENCE_UNIT_NAME_DIRECTSALES + " with ID=[" + key + "]");

        return (key == null) ? null : key.longValue();
    }

    public Salesman retrieveBy(final Long personID) {
        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();
        final SystemPerson sp = retrieveSalesmanSystemPerson(personID,
                jdbcTemplate);

        final Salesman salesman = mountSalesmanByPersonID(sp, personID);

        return salesman;
    }

    private SystemPerson retrieveSalesmanSystemPerson(final Long personID,
            final JdbcTemplate jdbcTemplate) {
        final List<SystemPerson> systemPersonL = retriveSystemPersonBy(
                jdbcTemplate, queryRetriveByID, personID);
        if ((systemPersonL == null) || (systemPersonL.isEmpty())) {
            final String msg = "ERROR: Salesman with personID=[" + personID
                    + "] not found!";
            LOG.error(msg);
            throw new BusinessException(msg,ExceptionsConstants.CODE_SALESMAN_NOTFOUND,ErrorType.BUSINESS);
        }
        if (systemPersonL.size() > 1) {
            final String msg = "ERROR: There are more then one Salesman with personID=["
                    + personID + "]. Cannot retrieve a single Salesman!";
            LOG.error(msg);
            throw new BusinessException(msg,ExceptionsConstants.CODE_SALESMAN_MOREONE,ErrorType.BUSINESS);
        }
        final SystemPerson sp = systemPersonL.get(0);
        return sp;
    }

    private SystemPerson retrieveSalesmanBy(final Long salesmanId,
            final JdbcTemplate jdbcTemplate) {
        final List<SystemPerson> systemPersonL = retriveSystemPersonBy(
                jdbcTemplate, queryRetrievePersonBySalesmanID, salesmanId);
        if ((systemPersonL == null) || (systemPersonL.isEmpty())) {
            final String msg = "ERROR: Salesman with salesmanId=[" + salesmanId
                    + "] not found!";
            LOG.error(msg);
            throw new BusinessException(msg,ExceptionsConstants.CODE_SALESMAN_NOTFOUND,ErrorType.BUSINESS);
        }
        if (systemPersonL.size() > 1) {
            final String msg = "ERROR: There are more then one Salesman with salesmanId=["
                    + salesmanId + "]. Cannot retrieve a single Salesman!";
            LOG.error(msg);
            throw new BusinessException(msg,ExceptionsConstants.CODE_SALESMAN_MOREONE,ErrorType.BUSINESS);
        }
        final SystemPerson sp = systemPersonL.get(0);
        return sp;
    }

    public Salesman retrieveBy(final Long salesmanID,
            final Long personID) {
        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();
        final SystemPerson sp = retrieveSalesmanSystemPerson(personID,
                jdbcTemplate);

        final Salesman salesman = mountSalesmanBySalesmanID(sp, salesmanID);

        return salesman;
    }

    public Long retrieveWorkshopIDBySalesmanID(final Long salesmanID)
            throws DataAccessException {
        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        return retrieveValueID(salesmanID, queryRetrieveSalesmanBySalesmanID,
                jdbcTemplate, new WorkshopIDRM());
    }

    private JdbcTemplate createTemplateJdbcForDSDirectSales() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                dataSourceDirectSales);
        return jdbcTemplate;
    }

    public Long retrieveRegionIDByWorkshopID(final Long workshopID)
            throws DataAccessException {
        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        return retrieveValueID(workshopID,
                queryRetrieveRegionAndBranchByBranchID, jdbcTemplate,
                new RegionIDRM());
    }

    public Long retrieveValueID(final Long field, final String query,
            final JdbcTemplate jdbcTemplate, final RowMapper<Long> rowMapper)
            throws DataAccessException {
        final Long valueID = jdbcTemplate.queryForObject(query,
                new Object[] { field }, rowMapper);

        return valueID;
    }

    public String retrieveWorkshopCodeBy(final Long workshopID)
            throws DataAccessException {
        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        final String workshopCode = jdbcTemplate.queryForObject(
                queryRetrieveRegionAndBranchByBranchID,
                new Object[] { workshopID }, new WorkshopCodeRM());

        return workshopCode;
    }

    public List<SalesmanScore> returnScoresByCriteria(final ProductDTO product,
            final Regional regional, final Workshop workshop,
            final Salesman salesman, final Calendar startDate,
            final Calendar endDate) {

        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        final List<SalesmanScore> scores = jdbcTemplate
                .query(querySalesmanScore(product, regional, workshop, salesman,
                        startDate, endDate), new SalesmanScoreRM());

        List<SalesmanScore> result = null;

        if (!scores.isEmpty()) {
            result = scores;
        }

        return result;
    }

    public Salesman retrieveSalesmanBy(final Long salesmanId) {
        LOG.debug("Try to retrieve Salesman Person with salesmanId=["
                + salesmanId + "]");

        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        final SystemPerson sp = retrieveSalesmanBy(salesmanId, jdbcTemplate);

        final Salesman salesman = jdbcTemplate.queryForObject(
                queryRetrieveSalesmanBySalesmanID, new Object[] { salesmanId },
                new SalesmanRM(sp));

        return salesman;
    }

    public Salesman retrieveSalesmanBy(final String cpfCnpj) {
        LOG.debug("Try to retrieve Salesman Person with CPF=[" + cpfCnpj + "]");

        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();
        final Long salesmanID = jdbcTemplate.queryForObject(
                queryRetrieveSalesmanIDByCPF, new Object[] { cpfCnpj, cpfCnpj },
                new SalesmanIDRM());
        final Long personID = jdbcTemplate.queryForObject(
                queryRetrieveSalesmanPersonIDBySalesmanID,
                new Object[] { salesmanID }, new SalesmanSPIDRM());

        return retrieveBy(salesmanID, personID);
    }

    private Salesman mountSalesmanByPersonID(final SystemPerson sp,
            final Long personID) {
        return mountSalesman(sp, queryRetrieveSalesmanByPersonID, personID);
    }

    private Salesman mountSalesman(final SystemPerson sp, final String query,
            final Long ID) {
        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        final Salesman salesman = jdbcTemplate.queryForObject(query,
                new Object[] { ID }, new SalesmanRM(sp));

        return salesman;
    }

    private Salesman mountSalesmanBySalesmanID(final SystemPerson sp,
            final Long salesmanID) {
        return mountSalesman(sp, queryRetrieveSalesmanBySalesmanID, salesmanID);
    }

    private String querySalesmanScore(final ProductDTO product,
            final Regional regional, final Workshop workshop,
            final Salesman salesman, final Calendar startDate,
            final Calendar endDate) {

        final StringBuilder query = new StringBuilder();
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        query.append(baseQueryRetrievePerformance);

        if ((product != null) && (product.getID() != null)) {
            query.append(" AND C.PRD_ID = ").append(product.getID().toString());
        }

        if ((workshop != null) && (workshop.getBranchCode() != null)) {
            query.append(" AND B.BCH_COD = '").append(workshop.getBranchCode())
                    .append("'");
        }

        if ((regional != null) && (regional.getDescription() != null)) {
            query.append(" AND R.REG_DSC LIKE '%")
                    .append((regional.getDescription()) + "%'");
        }
        if ((salesman != null) && (salesman.getID() != null)) {
            query.append(" AND C.SALESMAN_ID = ").append(salesman.getID());
        }

        query.append("  AND CERTIFICATE_EMS_DT     ");
        query.append("  BETWEEN CONVERT(DATETIME,'")
                .append(format.format(startDate.getTime())).append("',103)");
        query.append("  AND CONVERT(DATETIME,'")
                .append(format.format(endDate.getTime()))
                .append("', 103) + 0.999999999  ");
        query.append(
                "  GROUP BY PRD.PRD_DSC,  U.USER_COD_PRT, P.PERSON_NME, P.PERSON_CPF, C.SALESMAN_ID,  ");
        query.append(
                "  C.BCH_ID, C.PRD_ID, B.BCH_COD ,B.BCH_DSC, R.REG_ID , R.REG_DSC ");
        query.append("  ORDER BY P.PERSON_NME ");

        return query.toString();
    }

    /**
     * Atualização do ramo e da filial do vendedor pelo id da pessoa.
     *
     * @param salesman
     */
    public void updateSalesmanBranch(final Salesman salesman) throws DataAccessException{

        final JdbcTemplate jdbcTemplateDirectSales = new JdbcTemplate(
                dataSourceDirectSales);

        final int updateResult = jdbcTemplateDirectSales.update(
                updateSalesmanBranch,
                new Object[] { salesman.getSalesmanBch(),
                        salesman.getSalesmanAtuBch(), salesman.getBchId(),
                        salesman.getPersonId() });

        if (updateResult == 0) {
            final String message = "Salesman status update fail! personId=["
                    + salesman.getPersonId() + "]";
            LOG.error(message);
            throw new BusinessException(message,
                    ExceptionsConstants.CODE_SALESMAN_NOT_UPDATED,ErrorType.BUSINESS);
        }

        LOG.debug("Salesman status update all to Peding!");

    }
   
    /**
     * 
     * @param regionName nome da regional
     * @return ID da regional
     * @throws DataAccessException
     */
    public Long retrieveCodRegionForName(final String regionName) throws DataAccessException {
        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();
        final Long valueID = jdbcTemplate.queryForObject(queryRetrieveCodRegionForName, new Object[] { regionName },
                new RegionIDRM());
        return valueID;
    }

    /**
     * Pesquisar o ID da filial por código e região
     * @param salesman vendedor
     * @param regionID ID da regional
     * @return ID da filial
     */
    public Long findCodBranchForRegion(final Salesman salesman, final Long regionID) throws DataAccessException {
        LOG.debug("Try findBranchForRegion with REG_ID e BCH_COD=[" + salesman.getSalesmanBch() + "]");

        final JdbcTemplate jdbcTemplate = createTemplateJdbcForDSDirectSales();

        final Long valueID = jdbcTemplate.queryForObject(queryfindSalesmanBchByBranch,
                new Object[] { regionID, salesman.getSalesmanBch() }, new WorkshopIDRM());

        return valueID;
    }
    
}
