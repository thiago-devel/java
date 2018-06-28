package br.com.cardif.wsdirectsales.core.business.sale.impl.test;

import static br.com.cardif.directsales.commons.persistence.dao.util.CustomerSalesConstants.CREDIT_CARD_PAYMENT_TYPE;
import static br.com.cardif.directsales.commons.persistence.dao.util.CustomerSalesConstants.NATIONALITY_DEFAULT;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cardif.core.directsales.commons.dto.Customer;
import br.com.cardif.core.directsales.commons.dto.InsuranceCertificate;
import br.com.cardif.kernel.core.commons.dto.IdentityDTO;
import br.com.cardif.kernel.core.commons.enums.Document;
import br.com.cardif.wsdirectsales.core.business.customer.impl.CustomerBusinessImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestBusinessAppConfig.class })
@Ignore
public class CustomerSalesBusinessImplTest {

    public static final int PRODUCT_CARTAO_SUPER_PROTEGIDO_ID = 25;
    public static final int PRODUCT_PROTECAO_PREMIADA = 26;
    @Autowired
    private CustomerBusinessImpl impl;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Ignore
    public void testRetrieveCustomerSales() {

        final Customer customer = new Customer();
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("13702902848");
        customer.setIdentity(identity);

        final List<InsuranceCertificate> certificateList = impl.retrieveCustomerSales(customer);
        Assert.assertNotNull(certificateList);
        Assert.assertEquals(certificateList.size(), 7);
        Assert.assertNotNull(certificateList.get(5).getID());
        Assert.assertNotNull(certificateList.get(5).getCustomer());
        Assert.assertNotNull(certificateList.get(5).getCustomer().getNationality());
        Assert.assertEquals(certificateList.get(5).getCustomer().getNationality(), NATIONALITY_DEFAULT);
        Assert.assertNotNull(certificateList.get(5).getCapitalSeries());
        Assert.assertNotNull(certificateList.get(5).getCapitalSeries().getCapitalNumber());
        Assert.assertNotNull(certificateList.get(5).getCapitalSeries().getDescription());
        Assert.assertNotNull(certificateList.get(5).getPaymentMethod().getCardPayment());
        Assert.assertNotNull(certificateList.get(5).getPaymentMethod().getPaymentType());
        Assert.assertTrue(certificateList.get(5).getPaymentMethod().getPaymentType() == CREDIT_CARD_PAYMENT_TYPE);
        Assert.assertNotNull(certificateList.get(5).getPaymentMethod().getCardPayment().getCardDisplayName());
        Assert.assertEquals(certificateList.get(5).getPaymentMethod().getCardPayment().getCardDisplayName(),
                "angelina maria de araujo");
    }

    @Test
    @Ignore
    public void testRetrieveCustomerSalesNotFailNullPointer() {

        final Customer customer = new Customer();
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("21863033882");
        customer.setIdentity(identity);

        final List<InsuranceCertificate> certificates = impl.retrieveCustomerSales(customer);
        Assert.assertNull(certificates);
    }

    @Test
    @Ignore
    public void testRetrieveCustomerSalesCustomerWithoutCertificates() {

        final Customer customer = new Customer();
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("21863033882");
        customer.setIdentity(identity);

        final List<InsuranceCertificate> certificates = impl.retrieveCustomerSales(customer);
        Assert.assertNull(certificates);
    }

    @Test
    @Ignore
    public void testRetrieveCustomerSalesFailNullPointer() {

        final Customer customer = new Customer();
        final IdentityDTO identity = new IdentityDTO();
        identity.setDocumentType(Document.CPF);
        identity.setDocumentValue("21863033882");
        customer.setIdentity(identity);

        thrown.expect(NullPointerException.class);

        impl.retrieveCustomerSales(customer);
    }
}
