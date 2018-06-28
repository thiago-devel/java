package br.com.resseler.directsales.commons.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.MARITALSTATUSMARRIED;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.MARITALSTATUSSINGLE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_CPF;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_DRT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_DT_BTH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ID_ML;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ROL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_SUP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_TYPE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_PERSON;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.USER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetriveByCPF;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.resseler.directsales.commons.persistence.dao.rowmappers.AddressRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.PhoneRM;
import br.com.resseler.directsales.commons.persistence.dao.rowmappers.SystemPersonRM;
import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.PhoneDTO;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public class SystemPersonDAO {

    private static Logger log = LogManager.getLogger(SystemPersonDAO.class);
    @Autowired(required = true)
    protected javax.sql.DataSource dataSourceDirectSales;

    /**
     *
     * @param person
     * @return
     */
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
            "squid:CommentedOutCodeLine",
            "squid:S1200" }, justification = "Esses comentarios serao mantidos, pois tendem a ser utilizados no futuro.")
    public Long persist(final SystemPerson person) {

        if (person == null) {
            final String msg = "ERROR: can not persist a null SystemPerson!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PERSON_NULL, ErrorType.VALIDATION);
        }

        final String cpfCnpj = (person.getIdentity() == null) ? null
                : person.getIdentity().getDocumentValue();
        if ((cpfCnpj == null) || "".equals(cpfCnpj.trim())) {
            final String msg = "ERROR: can not persist a SystemPerson with invalid CPF=["
                    + cpfCnpj + "]!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PERSON_CPF_EMPTY,
                    ErrorType.VALIDATION);
        }
        /*
         * //descomentar para ativar a checagem de cliente pre-existente. final
         * List<SystemPerson> systemPerson = retrieveBy(cpfCnpj); if
         * ((systemPerson != null) && (!systemPerson.isEmpty())) { final String
         * msg = "ERROR: can not persist a SystemPerson that already exists!";
         * log.error(msg); throw new IllegalArgumentException(msg); }
         */

        final Number key = executeAndReturnKey(person);

        if (key == null) {
            log.error("Fail to persist Person[" + person + "] to "
                    + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        log.debug("Person[" + person + "] saved to "
                + PERSISTENCE_UNIT_NAME_DIRECTSALES + "with ID=[" + key + "]");

        return (key == null) ? null : key.longValue();
    }

    /**
     *
     * @param person
     * @return
     */
    private Number executeAndReturnKey(final SystemPerson person) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                dataSourceDirectSales);

        final Map<String, Object> args = prepareInsertParameters(person);

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_PERSON)
                .usingGeneratedKeyColumns(PERSON_ID);
        final Number key = insert.executeAndReturnKey(args);
        return key;
    }

    /**
     *
     * @param person
     * @return
     */
    private Map<String, Object> prepareInsertParameters(
            final SystemPerson person) {
        final Map<String, Object> args = new HashMap<>();
        args.put(PARTNER_ID, (person.getPartner() == null) ? null
                : person.getPartner().getID());
        args.put(USER_ID,
                (person.getLogin() == null) ? null : person.getLogin().getID());
        args.put(PERSON_NME, person.getFullName());
        args.put(PERSON_SUP, person.getSUP());
        args.put(PERSON_CPF, (person.getIdentity() == null) ? null
                : person.getIdentity().getDocumentValue());
        args.put("PERSON_RG", null);
        args.put("PERSON_GDR", null);
        args.put(PERSON_DT_BTH, (person.getBirthDate() == null) ? null
                : new java.sql.Date(person.getBirthDate().getTimeInMillis()));
        final Integer personMaritalStatus = fillMaritalStatus(person);
        args.put("PERSON_MRT", personMaritalStatus);
        args.put("PERSON_DDD", null);
        args.put("PERSON_PHO", null);
        args.put("PERSON_DDD_CEL", null);
        args.put("PERSON_PHO_CEL", null);
        args.put("PERSON_EML", null);
        args.put(PERSON_DRT, person.getDrtID());
        args.put(PERSON_ID_ML, person.getMlID());
        args.put(PERSON_ROL, person.getRole());
        args.put("PERSON_DDD_COM", null);
        args.put("PERSON_PHO_COM", null);
        args.put(PERSON_TYPE, person.getType());
        args.put("PERSON_FULL_NME_MOTHER", null);
        args.put("PERSON_RG_ORG_EMS", null);
        args.put("PERSON_PIS", null);
        args.put("PERSON_CNS", null);
        return args;
    }

    /**
     *
     * @param person
     * @return
     */
    private Integer fillMaritalStatus(final SystemPerson person) {
        Integer personMaritalStatus = null;
        if (person.getMaritalStatus() == MaritalStatus.SINGLE) {
            personMaritalStatus = MARITALSTATUSSINGLE;
        }
        if (person.getMaritalStatus() == MaritalStatus.MARRIED) {
            personMaritalStatus = MARITALSTATUSMARRIED;
        }
        return personMaritalStatus;
    }

    /**
     *
     * @param CPF
     * @return
     */
    public List<SystemPerson> retrieveBy(final String CPF) {

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                dataSourceDirectSales);
        final List<SystemPerson> sp = retriveSystemPersonBy(jdbcTemplate,
                queryRetriveByCPF, CPF);

        return sp;
    }

    /**
     *
     * @param jdbcTemplate
     * @param query
     * @param argument
     * @return
     */
    public static List<SystemPerson> retriveSystemPersonBy(
            final JdbcTemplate jdbcTemplate, final String query,
            final Object argument) {

        if (argument == null) {
            final String msg = "ERROR: can not retrieve SystemPerson with null argument!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_ARGUMENT_PERSON_NULL,
                    ErrorType.VALIDATION);
        }

        /**
         * using RowMapper anonymous class, we can create a separate RowMapper
         * for reuse
         */
        final List<SystemPerson> sp = jdbcTemplate.query(query,
                new SystemPersonRM(), argument);

        final List<SystemPerson> result = ((sp == null) || (sp.isEmpty()))
                ? null : sp;

        return result;
    }

    /**
     *
     * @param jdbcTemplate
     * @param query
     * @param argument
     * @return
     */
    public static List<AddressDTO> retriveAddressBy(
            final JdbcTemplate jdbcTemplate, final String query,
            final Object argument) {

        if (argument == null) {
            final String msg = "ERROR: can not retrieve Address with null argument!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_ARGUMENT_ADDRESS_NULL,
                    ErrorType.VALIDATION);
        }

        /**
         * using RowMapper anonymous class, we can create a separate RowMapper
         * for reuse
         */
        final List<AddressDTO> address = jdbcTemplate.query(query,
                new AddressRM(), argument);
        final List<AddressDTO> result = ((address == null)
                || (address.isEmpty())) ? null : address;

        return result;
    }

    /**
     *
     * @param jdbcTemplate
     * @param query
     * @param argument
     * @return
     */
    public static List<PhoneDTO> retrivePhoneBy(final JdbcTemplate jdbcTemplate,
            final String query, final Object argument) {

        if (argument == null) {
            final String msg = "ERROR: can not retrieve Phone with null argument!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_ARGUMENT_PHONE_NULL,
                    ErrorType.VALIDATION);
        }

        /**
         * using RowMapper anonymous class, we can create a separate RowMapper
         * for reuse
         */
        final List<PhoneDTO> address = jdbcTemplate.query(query, new PhoneRM(),
                argument, argument);
        final List<PhoneDTO> result = ((address == null) || (address.isEmpty()))
                ? null : address;

        return result;
    }
}
