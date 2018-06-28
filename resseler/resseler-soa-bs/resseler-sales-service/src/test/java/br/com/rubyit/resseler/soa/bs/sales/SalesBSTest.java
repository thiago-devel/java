package br.com.rubyit.resseler.soa.bs.sales;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CREDIT_CARD_PAYMENT_TYPE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_CARTAO_SUPERPROTEGIDO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_PROTECAO_PREMIADA;
import static br.com.resseler.directsales.sales.persistence.dao.test.CardPaymentTest.CPF_EXEMPLO;
import static br.com.resseler.directsales.sales.persistence.dao.test.CardPaymentTest.NOME_CARTAO_EXEMPLO;
import static br.com.resseler.directsales.sales.persistence.dao.test.CardPaymentTest.montaCartaoParaTestes;
import static br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants.MSG_1_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
import static org.hamcrest.CoreMatchers.startsWith;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.Contacts;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.dto.PhoneDTO;
import br.com.rubyit.resseler.commons.kernel.enums.AddressType;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.commons.kernel.enums.Gender;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.commons.kernel.enums.PhoneType;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SalesmanScore;
import br.com.rubyit.resseler.core.commons.dto.UserDTO;
import br.com.rubyit.resseler.soa.bs.sales.config.BSTestAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BSTestAppConfig.class })
@WebAppConfiguration
@Ignore
public class SalesBSTest {

    @Autowired
    private ProductsAndCategoriesBS service;
    private static final String PASSWORD_LOGIN = "0000100";
    private static final String USERNAME_LOGIN = PASSWORD_LOGIN;
    private static final int DEFAULT_PARTNER = 142;
    private static final String CARTAO_ENCRIPTADO = "6D97C368B561CC3C8F323230F7744D2058A7DCDF999E912B";
	private final Salesman salesMan = new Salesman();

    @Before
    public void setup() {
        Assert.assertNotNull(service);
    }
    
    @Test
    public void testLoadProductsAndCategoriesSuccess() {
    	throw new NotImplementedException();
    	
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void createSalesman() {
        salesMan.setFullName("DIEGO MAURICIO DE JESUS OLIVEIRA");
        final Calendar cardValidityDate = Calendar.getInstance();
        cardValidityDate.set(Calendar.YEAR, 2018);
        salesMan.setBirthDate(cardValidityDate);
        salesMan.setIdentity(new IdentityDTO());
        salesMan.getIdentity().setDocumentValue("14806756601");;
        salesMan.setPartner(new Partner());
        salesMan.getPartner().setID(142L);
        salesMan.setRole("VENDEDOR");
        salesMan.setSalesmanCod("DIEGO");
        salesMan.setSalesmanBch(264);
        salesMan.setSalesmanAtuBch(264);
        salesMan.setBchId(1220L);
    }

    private CardPaymentDTO createCardEncriptedForTestCheckUpdateMethod() {
        final CardPaymentDTO creditCard = new CardPaymentDTO();
        creditCard.setCardDisplayName("MARIA L B SANTOS");
        creditCard.setCardFlag("LUIZA");
        // creditCard.setCardNumber("5305993016354689");
        creditCard.setCardNumber(CARTAO_ENCRIPTADO);
        creditCard.setCardSecurityCode("000");
        creditCard.setCardValue(BigDecimal.ONE);
        final Calendar cardValidityDate = Calendar.getInstance();
        cardValidityDate.set(Calendar.YEAR, 2018);
        creditCard.setCardValidity(cardValidityDate);
        return creditCard;
    }

    private CardPaymentDTO createCardForTestCheckUpdateMethod() {
        final CardPaymentDTO creditCard = new CardPaymentDTO();
        creditCard.setCardDisplayName("MARIA L B SANTOS");
        creditCard.setCardFlag("LUIZA");
        creditCard.setCardNumber("5305993016354689");
        creditCard.setCardSecurityCode("000");
        creditCard.setCardValue(BigDecimal.ONE);
        final Calendar cardValidityDate = Calendar.getInstance();
        cardValidityDate.set(Calendar.YEAR, 2018);
        creditCard.setCardValidity(cardValidityDate);
        return creditCard;
    }
}
