package br.com.resseler.directsales.commons.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_CONTRACT;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants;
import br.com.rubyit.resseler.core.commons.dto.UserDTO;

public class UserDAO {

    private static Logger LOG = LogManager.getLogger(UserDAO.class);
    @Autowired(required = true)
    protected javax.sql.DataSource dataSourceDirectSales;

    /**
     * 
     * @param username
     * @param passwd
     * @return Boolean
     */
    public Boolean isValidUser(final String username, final String passwd) {

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);
        final List<Object> user = jdbcTemplate.query("SELECT * FROM USERS WHERE USER_COD = ? AND USER_PWD = ?",
                new RowMapper<Object>() {
                    @Override
                    public Object mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                        LOG.debug("User [" + username + "] found");
                        return new Object();
                    }
                }, username, passwd);

        Boolean userValid = true;
        if ((user == null) || user.isEmpty()) {
            LOG.error("ERROR: user [" + username + "] not found!");
            userValid = false;
        }

        return userValid;
    }

	public Long persist(final UserDTO user) {

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSourceDirectSales)
                .withTableName(CustomerSalesConstants.TABLE_USERS)
                .usingGeneratedKeyColumns(CustomerSalesConstants.USER_ID);
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CustomerSalesConstants.PARTNER_ID, user.getPartnerId());
        parameterSource.addValue(CustomerSalesConstants.PROFILE_ID, user.getProfileId());
        parameterSource.addValue(CustomerSalesConstants.USER_COD, user.getUserCod());
        parameterSource.addValue(CustomerSalesConstants.USER_PASS, user.getUserPwd());
        parameterSource.addValue(CustomerSalesConstants.USER_ACT, user.getUserAct());
        parameterSource.addValue(CustomerSalesConstants.USER_WORD, user.getUserWord());
        parameterSource.addValue(CustomerSalesConstants.USER_GRP, user.getUserGrp());

        final SqlParameterSource parameters = parameterSource;
        final Number key = insert.executeAndReturnKey(parameters);

        if (key == null) {
            LOG.error(
                    "Fail to persist User with parans=[" + user + "] update data to " + PERSISTENCE_UNIT_NAME_CONTRACT);
        }

        LOG.debug("Certificate with User=[" + user + "] update data saved to " + PERSISTENCE_UNIT_NAME_CONTRACT
                + " with ID=[" + key + "]");

        return (key == null) ? null : key.longValue();
    }

}
