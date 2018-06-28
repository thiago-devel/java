package br.com.resseler.directsales.commons.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ENTERPRISE_TYPE_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_COD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_CAP_SER_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_FLG_CAP_SER;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_FLG_CRT_NBR_FT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_VL_COMIS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_CERTIF_PRINT_FG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_CERT_PREFIX;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_DSC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_EFF_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_EMP_CAP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_INS_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MAX_CERT_CPF;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MAX_INSURED_AGE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MP_NO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_PLAN_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_PRZ_VIG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_SLM_PRT_PCT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_SLM_PRT_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_WORKFLOW_STEPS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_PRD_PRODUCT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TEF_AGREEMENT_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveMaxAgeInsuredProduct;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveProduct;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveProductPrefix;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.resseler.directsales.commons.persistence.dao.rowmappers.InsuranceDTORM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.ProductComissionValueRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.ProductMaxAgeInsuredRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.ProductPrefixRM;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.exceptions.BusinessException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public class ProductInsuranceDAO {

    private static Logger LOG = LogManager.getLogger(ProductInsuranceDAO.class);

    @Autowired(required = true)
    protected javax.sql.DataSource dataSourceDirectSales;

    /**
     * 
     * @param certificate
     * @param parans
     * @return
     */
    public Integer persist(final InsuranceCertificate certificate, final Map<String, Object> parans) {

        if (certificate == null) {
            final String msg = "ERROR: can not persist a null InsuranceCertificate!";
            LOG.error(msg);
            throw new ValidationException(msg,ExceptionsConstants.CODE_CERTIFICATE_NULL,ErrorType.VALIDATION);
        }
        if (certificate.getProduct() == null) {
            final String msg = "ERROR: can not persist a null Product (certificate.getProduct())!";
            LOG.error(msg);
            throw new BusinessException(msg,ExceptionsConstants.CODE_PRODUCT_NULL,ErrorType.VALIDATION);
        }

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);

        final Map<String, Object> args = new HashMap<>();
        args.put(PARTNER_ID, parans.get(PARTNER_ID));
        args.put(PRD_NME, parans.get(PRD_NME));
        args.put(PRD_DSC, (certificate.getProduct() == null) ? null : certificate.getProduct().getDescription());
        args.put(PRD_EFF_DT, new java.sql.Date(((Date) parans.get(PRD_EFF_DT)).getTime()));
        args.put("PRD_EXP_DT", parans.get("PRD_EXP_DT"));
        args.put(PRD_INS_DTM, parans.get(PRD_INS_DTM));
        args.put("PRD_INS_USER_ID", parans.get("PRD_INS_USER_ID"));
        args.put("PRD_UPD_DTM", parans.get("PRD_UPD_DTM"));
        args.put("PRD_UPD_USER_ID", parans.get("PRD_UPD_USER_ID"));
        args.put(PRD_SLM_PRT_VL, parans.get(PRD_SLM_PRT_VL));
        args.put(PRD_SLM_PRT_PCT, parans.get(PRD_SLM_PRT_PCT));
        args.put(PRD_PRZ_VIG, parans.get(PRD_PRZ_VIG));
        args.put(PRD_AGR_ID, parans.get(PRD_AGR_ID));
        args.put(PRD_MP_NO, parans.get(PRD_MP_NO));
        args.put(PRD_AGR_VL_COMIS, parans.get(PRD_AGR_VL_COMIS));
        args.put(PRD_AGR_FLG_CAP_SER, parans.get(PRD_AGR_FLG_CAP_SER));
        args.put(PRD_AGR_CAP_SER_VL, parans.get(PRD_AGR_CAP_SER_VL));
        args.put(PRD_PLAN_ID, parans.get(PRD_PLAN_ID));
        args.put(PRD_CERTIF_PRINT_FG, parans.get(PRD_CERTIF_PRINT_FG));
        args.put(PRD_WORKFLOW_STEPS, parans.get(PRD_WORKFLOW_STEPS));
        args.put(PRD_MAX_CERT_CPF, parans.get(PRD_MAX_CERT_CPF));
        args.put(PRD_MAX_INSURED_AGE, parans.get(PRD_MAX_INSURED_AGE));
        args.put(PRD_EMP_CAP, parans.get(PRD_EMP_CAP));
        args.put(PRD_CERT_PREFIX, parans.get(PRD_CERT_PREFIX));
        args.put(PRD_AGR_FLG_CRT_NBR_FT, parans.get(PRD_AGR_FLG_CRT_NBR_FT));
        args.put("PRD_SUM_CAR_VL", parans.get("PRD_SUM_CAR_VL"));
        args.put(PARTNER_COD, parans.get(PARTNER_COD));
        args.put("PRD_ELEGIABLE_CAR_MAX_AGE", parans.get("PRD_ELEGIABLE_CAR_MAX_AGE"));
        args.put("PRD_ELEGIABLE_CAR_MAX_FIPE_PRICE", parans.get("PRD_ELEGIABLE_CAR_MAX_FIPE_PRICE"));
        args.put("PRD_MIN_FINANCING_VALUE", parans.get("PRD_MIN_FINANCING_VALUE"));
        args.put("PRD_MAX_FINANCING_VALUE", parans.get("PRD_MAX_FINANCING_VALUE"));
        args.put(ENTERPRISE_TYPE_ID, parans.get(ENTERPRISE_TYPE_ID));
        args.put(TEF_AGREEMENT_ID, parans.get(TEF_AGREEMENT_ID));

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_PRD_PRODUCT)
                .usingGeneratedKeyColumns(PRD_ID);
        final Number key = insert.executeAndReturnKey(args);

        if (key == null) {
            LOG.error("Fail to persist Product[" + certificate.getProduct() + "] to "
                    + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        LOG.debug("Product[" + certificate.getProduct() + "] saved to " + PERSISTENCE_UNIT_NAME_DIRECTSALES
                + " with ID=[" + key + "]");

        return (key == null) ? null : key.intValue();
    }

    /**
     * 
     * @param productID
     * @return
     */
    public String retrieveProductPrefix(final Long productID) {
        LOG.debug("Init method retrieveProductPrefix(final Integer [" + productID + "])");

        if (productID == null) {
            final String msg = "ERROR: productID is Null! Can not retrieve product prefix!";
            LOG.error(msg);
            throw new BusinessException(msg,ExceptionsConstants.CODE_PRODUCTPREFIXID_NULL,ErrorType.VALIDATION);
        }

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);

        final String productPrefix = jdbcTemplate.queryForObject(queryRetrieveProductPrefix, new Object[] { productID },
                new ProductPrefixRM());

        LOG.debug("End method retrieveProductPrefix(final Integer [" + productID + "])");
        return productPrefix;
    }

    /**
     * 
     * @param productID
     * @return
     */
    public InsuranceDTO retrieveProduct(final Long productID) {

        validateProductID(productID);

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);

        final InsuranceDTO product = jdbcTemplate.queryForObject(queryRetrieveProduct, new Object[] { productID },
                new InsuranceDTORM());

        return product;
    }

    /**
     * 
     * @param productID
     * @return
     */
    public BigDecimal retrieveProductComissionValue(final Long productID) {

        validateProductID(productID);

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);

        final BigDecimal productComissionValue = jdbcTemplate.queryForObject(queryRetrieveProduct, new Object[] { productID },
                new ProductComissionValueRM());

        return productComissionValue;
    }

    /**
     * 
     * @param productID
     */
    private void validateProductID(final Long productID) {
        if (productID == null) {
            final String msg = "ERROR: productID is Null! Can not retrieve product!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PRODUCTID_NULL,ErrorType.VALIDATION);
        }
    }

    /**
     * recuperar a idade maxima para contratar o seguro
     * 
     * @param productID
     *            código do produto da venda
     * @return
     */
    public Integer retrieveMaxInsuredAge(Long productID) {
        validateProductID(productID);

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);

        final Integer maxAgeInsured = jdbcTemplate.queryForObject(queryRetrieveMaxAgeInsuredProduct,
                new Object[] { productID }, new ProductMaxAgeInsuredRM());

        return maxAgeInsured;
    }

}
