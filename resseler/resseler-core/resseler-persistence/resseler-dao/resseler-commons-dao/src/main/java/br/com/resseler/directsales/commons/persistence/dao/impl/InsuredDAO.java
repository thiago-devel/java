package br.com.resseler.directsales.commons.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_CMP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_CTY;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_NBH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_NUM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_STE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_ZIP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INSURED_COD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INSURED_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_ADDRESS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_INSURED;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.enums.AddressType;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public class InsuredDAO {

    private static Logger log = LogManager.getLogger(InsuredDAO.class);

    @Autowired(required = true)
    protected javax.sql.DataSource dataSourceDirectSales;
    @Autowired
    protected SystemPersonDAO daoSPerson;
    private JdbcTemplate jdbcTemplate = null;

    @PostConstruct
    public void init() {

        jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);
    }

    /**
     * 
     * @param customer
     * @return
     */
    @Transactional(value = "transactionManagerDirectSales")
    public Long persist(final Customer customer) {

        if (customer == null) {
            final String msg = "ERROR: can not persist a null Customer!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_COSTUMER_NULL,ErrorType.VALIDATION);
        }

        final Number personKey = daoSPerson.persist(customer);
        final Integer personID = (personKey == null) ? null : personKey.intValue();

        final Number key = persistCustomer(customer, personID);
        customer.setID((key == null) ? null : key.longValue());

        persistCustomerAddres(customer, personID);

        return (key == null) ? null : key.longValue();
    }

    /**
     * 
     * @param customer
     * @param personID
     * @return
     */
    @Transactional(value = "transactionManagerDirectSales")
    public Number persistCustomer(final Customer customer, final Integer personID) {

        final Map<String, Object> args = new HashMap<>();

        args.put(PERSON_ID, personID);
        args.put(INSURED_COD, null);

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_INSURED)
                .usingGeneratedKeyColumns(INSURED_ID);
        final Number key = insert.executeAndReturnKey(args);

        if (key == null) {
            log.error("Fail to persist Insured[" + customer + "] to " + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        log.debug("Insured[" + customer + "] saved to " + PERSISTENCE_UNIT_NAME_DIRECTSALES + " with ID=[" + key + "]");

        return key;
    }

    /**
     * 
     * @param customer
     * @param personID
     * @return
     */
    @Transactional(value = "transactionManagerDirectSales")
    public Number persistCustomerAddres(final Customer customer, final Integer personID) {

        if (customer == null) {
            final String msg = "ERROR: can not persist Customer Address with null Customer!";
            log.error(msg);
            throw new ValidationException(msg,ExceptionsConstants.CODE_COSTUMERADDRESS_NULL,ErrorType.VALIDATION);
        }
        final List<ContactDTO> contacts = customer.retrieveContacts();
        if ((contacts == null) || contacts.isEmpty()) {
            final String msg = "ERROR: can not persist Customer Address with invalid Customer Contacts=[" + contacts
                    + "]!";
            log.error(msg);
            throw new ValidationException(msg,ExceptionsConstants.CODE_COSTUMERCONTACT_EMPTY,ErrorType.VALIDATION);
        }
        final AddressDTO addressRes = fillResidentialAddress(contacts);

        final Number key = executeAndReturnKey(personID, addressRes);

        if (key == null) {
            log.error("Fail to persist Customer Address=[" + addressRes + "] to " + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        log.debug("Customer Address[" + addressRes + "] saved to " + PERSISTENCE_UNIT_NAME_DIRECTSALES + " with ID=["
                + key + "]");

        return key;
    }

    /**
     * 
     * @param contats
     * @return
     */
    private AddressDTO fillResidentialAddress(final List<ContactDTO> contats) {
        AddressDTO addressRes = null;
        for (final ContactDTO contact : contats) {
            if ((contact.getAddress() != null) && (contact.getAddress().getType() == AddressType.RESIDENTIAL)) {
                addressRes = contact.getAddress();
            }
        }
        return addressRes;
    }

    /**
     * 
     * @param personID
     * @param addressRes
     * @return
     */
    private Number executeAndReturnKey(final Integer personID, final AddressDTO addressRes) {

        final Map<String, Object> args = prepareInsertArguments(personID, addressRes);

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_ADDRESS)
                .usingGeneratedKeyColumns(ADDRESS_ID);
        final Number key = insert.executeAndReturnKey(args);
        return key;
    }

    /**
     * 
     * @param personID
     * @param addressRes
     * @return
     */
    private Map<String, Object> prepareInsertArguments(final Integer personID, final AddressDTO addressRes) {
        final Map<String, Object> args = new HashMap<>();
        args.put(PERSON_ID, personID);
        if (addressRes != null) {
            args.put(ADDRESS_NME, addressRes.getAddressDetail());
            args.put(ADDRESS_NUM, addressRes.getAddressNumber());
            args.put(ADDRESS_CMP, addressRes.getAddressReference());
            args.put(ADDRESS_NBH, addressRes.getNeighborhood());
            args.put(ADDRESS_CTY, addressRes.getCity());
            args.put(ADDRESS_STE, addressRes.getState());
            args.put(ADDRESS_ZIP, addressRes.getAddressPostalCode());
        }
        return args;
    }
}
