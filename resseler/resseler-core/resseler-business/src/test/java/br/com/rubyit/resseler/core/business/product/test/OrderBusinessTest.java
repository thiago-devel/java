package br.com.rubyit.resseler.core.business.product.test;

import static br.com.rubyit.resseler.core.business.sale.impl.test.CardPaymentTest.CPF_EXEMPLO;
import static br.com.rubyit.resseler.core.business.sale.impl.test.CardPaymentTest.NOME_CARTAO_EXEMPLO;
import static br.com.rubyit.resseler.core.business.sale.impl.test.CardPaymentTest.montaCartaoParaTestes;
import static br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants.MSG_1_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
import static br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants.MSG_3_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
import static br.com.rubyit.resseler.kernel.ResselerSystemProperties.IMAGES_PRODUCTS_PATH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CREDIT_CARD_PAYMENT_TYPE;
import static org.hamcrest.CoreMatchers.startsWith;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.Email;
import br.com.rubyit.resseler.commons.core.MainStock;
import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.ProductAttributes;
//import br.com.rubyit.resseler.commons.core.ProductPrice;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.core.infra.Image;
import br.com.rubyit.resseler.commons.core.infra.Images;
import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.Contacts;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.dto.PhoneDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.commons.kernel.dto.Stock;
import br.com.rubyit.resseler.commons.kernel.enums.AddressType;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.commons.kernel.enums.Gender;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;
import br.com.rubyit.resseler.commons.kernel.enums.PhoneType;
import br.com.rubyit.resseler.core.business.product.ProductBusiness;
import br.com.rubyit.resseler.core.business.sale.impl.SaleBusinessImpl;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.kernel.ResselerSystemProperties;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestBusinessProductAppConfig.class })
@Ignore
public class OrderBusinessTest {

    private static final String PASSWORD_LOGIN = "0000100";
    private static final String USERNAME_LOGIN = PASSWORD_LOGIN;
    private static final int DEFAULT_PARTNER = 142;
    public static final int PRODUCT_CARTAO_SUPER_PROTEGIDO_ID = 25;
    public static final int PRODUCT_PROTECAO_PREMIADA = 26;
    @Autowired
    private ProductBusiness business;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testCreateAProductOrder() {
    	
    	//implementar
    }
    
    @Test
    public void testFindAProductOrder() {
    	
    	//implementar
    }
    
    @Test
    public void testUpdateAProductOrder() {
    	
    	//implementar
    }
    
}
