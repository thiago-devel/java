package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.MARITALSTATUSMARRIED;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.MARITALSTATUSSINGLE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_CPF;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_DRT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_DT_BTH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_GDR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ID_ML;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_MRT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ROL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_SUP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_TYPE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.USER_ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.commons.kernel.enums.Gender;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;

public class SystemPersonRM implements RowMapper<SystemPerson> {

    /**
     * @param rs
     * @param rowNum
     */
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
            "squid:CommentedOutCodeLine",
            "squid:S1200" }, justification = "Esses comentarios serao mantidos, pois tendem a ser utilizados no futuro.")
    @Override
    public SystemPerson mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {
        final SystemPerson person = new SystemPerson();

        person.setID(rs.getLong(PERSON_ID));
        final LoginDTO login = new LoginDTO();
        login.setID(rs.getLong(USER_ID));
        person.setLogin(login);
        final Partner partnerLocal = new Partner();
        partnerLocal.setID(rs.getLong(PARTNER_ID));
        person.setPartner(partnerLocal);
        person.setFullName(rs.getString(PERSON_NME));
        person.setSUP(rs.getInt(PERSON_SUP));
        final IdentityDTO identityCPF = new IdentityDTO();
        identityCPF.setDocumentType(Document.CPF);
        identityCPF.setDocumentValue(rs.getString(PERSON_CPF));
        person.setIdentity(identityCPF);
        // String personRg;
        person.setGender(translateGender(rs.getString(PERSON_GDR)));
        final Calendar c = GregorianCalendar.getInstance();
        final Date date = rs.getDate(PERSON_DT_BTH);
        if (date != null) {
            c.setTime(date);
        }
        person.setBirthDate(c);
        person.setMaritalStatus(fillMaritalStatus(rs.getInt(PERSON_MRT)));
        // String personDdd;
        // String personPho;
        // String personDddCel;
        // String personPhoCel;
        // String personDddCom;
        // String personPhoCom;
        // String personEml;
        person.setDrtID(rs.getInt(PERSON_DRT));
        person.setMlID(rs.getInt(PERSON_ID_ML));
        person.setRole(rs.getString(PERSON_ROL));
        person.setType(rs.getInt(PERSON_TYPE));
        // String personFullNmeMother;
        // String personRgOrgEms;
        // String personPis;
        // String personCns;

        return person;
    }

    /**
     *
     * @param gender
     * @return
     */
    private Gender translateGender(final String gender) {
        if (gender != null) {
            switch (gender) {
            case "M":
                return Gender.MALE;
            case "F":
                return Gender.FEMALE;
            default:
            }

        }

        return null;
    }

    /**
     *
     * @param maritalStatusValue
     * @return
     */
    private MaritalStatus fillMaritalStatus(final Integer maritalStatusValue) {
        MaritalStatus maritalStatus = null;
        if (MARITALSTATUSSINGLE.equals(maritalStatusValue)) {
            maritalStatus = MaritalStatus.SINGLE;
        }
        if (MARITALSTATUSMARRIED.equals(maritalStatusValue)) {
            maritalStatus = MaritalStatus.MARRIED;
        }
        return maritalStatus;
    }
}
