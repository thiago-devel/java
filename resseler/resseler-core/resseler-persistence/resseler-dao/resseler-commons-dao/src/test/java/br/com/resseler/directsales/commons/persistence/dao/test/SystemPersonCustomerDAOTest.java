package br.com.resseler.directsales.commons.persistence.dao.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO;
import br.com.resseler.directsales.commons.persistence.dao.test.config.TestDAOMockAppConfig;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDAOMockAppConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemPersonCustomerDAOTest {

    public static final Long DEFAULT_PARTNER_ID = 142L;
    private static final String COMPRADOR = "COMPRADOR";
    @Autowired
    private SystemPersonDAO daoSPerson;
    @Autowired
    private javax.sql.DataSource dataSourceDirectSales;
    private static final String customerCPF = "6600693851";
    private static final String customerWillian = "WILLIAM FEIJO SILVA";
    private Partner partner;

    @Before
    public void setup() {
        Assert.assertNotNull(dataSourceDirectSales);
        Assert.assertNotNull(daoSPerson);
    }

    @Test
    public void retrivePersonCustomerWillian() {
        final Number key = gravaPersonCustomer(daoSPerson);
        Assert.assertNotNull(key);

        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER_ID);

        final List<SystemPerson> personL = daoSPerson.retrieveBy(customerCPF);
        final SystemPerson person = personL.get(0);
        Assert.assertNotNull(person);
        Assert.assertEquals(customerWillian, person.getFullName());
        /*Assert.assertEquals(MaritalStatus.MARRIED,person.getMaritalStatus());*/
        Assert.assertEquals(customerCPF, person.getIdentity().getDocumentValue());
    }

    public static Long gravaPersonCustomer(final SystemPersonDAO daoSPerson) {
        final SystemPerson customer = new SystemPerson();
        customer.setID(10601L);
        final Partner partner = new Partner();
        partner.setID(DEFAULT_PARTNER_ID);
        customer.setPartner(partner);
        final LoginDTO loginC = new LoginDTO();
        loginC.setID(24L);
        customer.setLogin(loginC);
        customer.setFullName(customerWillian);
        customer.setSUP(1);
        final IdentityDTO cCPF = new IdentityDTO();
        cCPF.setDocumentType(Document.CPF);
        cCPF.setDocumentValue(customerCPF);
        customer.setIdentity(cCPF);
        final Calendar dataNascimentoC = new GregorianCalendar();
        dataNascimentoC.set(Calendar.YEAR, 1979);
        dataNascimentoC.set(Calendar.MONTH, 6);
        dataNascimentoC.set(Calendar.DAY_OF_MONTH, 19);
        customer.setBirthDate(dataNascimentoC);
        customer.setDrtID(315);
        customer.setMlID(99059);
        customer.setRole(COMPRADOR);
        customer.setType(1);
        customer.setMaritalStatus(MaritalStatus.MARRIED);

        final Long key = daoSPerson.persist(customer);

        return key;
    }

}
