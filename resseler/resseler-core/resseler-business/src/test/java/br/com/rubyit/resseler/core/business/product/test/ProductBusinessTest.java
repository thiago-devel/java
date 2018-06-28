package br.com.rubyit.resseler.core.business.product.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.Price;
import br.com.rubyit.resseler.commons.core.ProductOfTheCompany;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.business.product.ProductBusiness;

@org.junit.Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestBusinessProductAppConfig.class })
public class ProductBusinessTest extends br.com.rubyit.resseler.commons.core.base.tests.TestPrototypes {

    private static final String PASSWORD_LOGIN = "0000100";
    private static final String USERNAME_LOGIN = PASSWORD_LOGIN;
    private static final int DEFAULT_PARTNER = 142;
    public static final int PRODUCT_CARTAO_SUPER_PROTEGIDO_ID = 25;
    public static final int PRODUCT_PROTECAO_PREMIADA = 26;
    @Autowired
    private ProductBusiness business;
    protected static Logger LOG = LogManager.getLogger(ProductBusinessTest.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @org.junit.Ignore
    @Test
    public void testCreateNewProductBatomAvonAndSaveByCompany() {
    	
    	ProductDTO batonAvon = new ProductDTO();
    	batonAvon.setName("Batom Ultramatte");
    	batonAvon.setDescription("Batom Matte Ultra Color Ultramatte FPS 15 3,6g");
    	batonAvon.appendProductDetailDescription("O Batom Ultramatte da Ultra Color possui acabamento matte aveludado,");
    	batonAvon.appendProductDetailDescription("oferecendo cobertura uniforme e sem brilho. Oferece hidratação garantida, ");
    	batonAvon.appendProductDetailDescription("não deixa seus lábios ressecados e desliza suavemente durante a ");
    	batonAvon.appendProductDetailDescription("aplicação. Além disso, tem proteção solar FPS 15 e conta com 10 opções de ");
    	batonAvon.appendProductDetailDescription("cores intensas e que duram horas. Sinta-se confortável para ser você, ");
    	batonAvon.appendProductDetailDescription("escolha sua cor favorita e experimente!");
    	
    	Price batonAvonPrice = new Price();
    	batonAvonPrice.putArbitraryPrice(BigDecimal.valueOf(21,99));
    	batonAvon.setProductPrice(batonAvonPrice);
    	
    	Category categoriaProdutosBatom = createTestProductCategoryTree();
    	
    	ModelBrand modeloBatomAvon = new ModelBrand();
    	modeloBatomAvon.setName("Ultra Color");
    	modeloBatomAvon.setCode("Ultramatte");
    	
    	Company company = new Company();
    	company.setName("AVON");
    	
    	Brand brandAvon = new Brand();
    	brandAvon.setName("BATONS AVON");
    	
    	business.saveProductsCatalogBy(company); // Aqui eu devo salvar uma lista de produtos com categoria e marca

    	
    	List<ProductDTO> products = business.retrieveProductsCatalogBy(company.getID()); //aqui eu devo conseguir recuperar o que foi salvo
    	
    	Assert.assertNotNull(products);
    	Assert.assertEquals(1, products.size());
    	ProductDTO productBatonAvon = products.get(0);
    	Assert.assertNotNull(productBatonAvon);
    	//Assert.assertEquals(brandAvon, productBatonAvon.getProductBrand());
    	//Assert.assertEquals(categoriaProdutosBatom, productBatonAvon.getProductCategory());
    	Assert.assertEquals(batonAvonPrice, productBatonAvon.getProductPrice());
    	Assert.assertEquals(categoriaProdutosBatom, productBatonAvon.getProductPrice());
    	
    	/*  --Vou deixar esse codigo aqui para nao esquecer depois se realmente é necessário.
    	Stock stockDeProdutosDoVendedor = new Stock();
    	stockDeProdutosDoVendedor.retrieveProductsStock().add(batonAvon);
    	
    	Salesman vendedoraMaria = new Salesman();
    	vendedoraMaria.setFullName("Maria do Rosario");
    	Calendar dataDeNascimento = new GregorianCalendar();
    	dataDeNascimento.set(Calendar.DAY_OF_MONTH, 1);
    	dataDeNascimento.set(Calendar.MONTH, 1);
    	dataDeNascimento.set(Calendar.YEAR, 1979);
    	LoginDTO loginVendedoraMaria = new LoginDTO();
    	loginVendedoraMaria.setUsername("mrosario@gmail.com");
    	loginVendedoraMaria.setUsername("bum1234");
    	ContactDTO contatos = new ContactDTO();
    	Email email = new Email("mrosario@gmail.com");
    	contatos.setEmail(email);
    	vendedoraMaria.retrieveContacts().add(contatos);
    	vendedoraMaria.setStock(stockDeProdutosDoVendedor);
    	
    	MainStock estoquePrincipal = new MainStock();
    	estoquePrincipal.addStockFromSalesman(vendedoraMaria);
    	*/
    	// salvar produto no estoque e recuperar depois, recuperando:
    	//categoria, detalhes do produto e do vendedor que o vende
    	
    }
    
    @Test
    public void test02LoadListOfProductsOfTheCompany() {
    	ModelBrand modeloBatomAvon = createTestModelBrand();
    	ModelBrand modeloBatomIntense = createTestModelBrand01();
    	
    	ProductDTO batonAvon = createTestProductBatonAvon(modeloBatomAvon);
    	ProductDTO batonBoticarioIntense = createTestProductBatomBoticarioIntense(modeloBatomIntense);
    	
    	Category categoriaProdutosBatom = createTestProductCategoryTree();
    	
    	Company companyAvon = createTestCompany();
    	Company companyBoticario = createTestCompanyBoticario();
    	
    	Brand brandAvon = createTestBrand();
    	Brand brandBatonsBoticario = createTestBrand01();
    	
    	ProductOfTheCompany productOfTheCompanyAvon = new ProductOfTheCompany();
    	productOfTheCompanyAvon.setProduct(batonAvon);
    	productOfTheCompanyAvon.setCategory(categoriaProdutosBatom);
    	productOfTheCompanyAvon.setBrand(brandAvon);
    	productOfTheCompanyAvon.setCompany(companyAvon);
    	
    	ProductOfTheCompany productOfTheCompanyBoticario = new ProductOfTheCompany();
    	productOfTheCompanyBoticario.setProduct(batonBoticarioIntense);
    	productOfTheCompanyBoticario.setCategory(categoriaProdutosBatom);
    	productOfTheCompanyBoticario.setBrand(brandBatonsBoticario);
    	productOfTheCompanyBoticario.setCompany(companyBoticario);
    	
    	List<ProductOfTheCompany> products = new ArrayList<>();
    	products.add(productOfTheCompanyAvon);
    	products.add(productOfTheCompanyBoticario);
    	
    	final Boolean result = business.save(products);
    	
    	List<ProductDTO> anotherProducts = business.retrieveProductsCatalogBy(categoriaProdutosBatom.getID()); //aqui eu devo conseguir recuperar o que foi salvo
    	
    	Assert.assertTrue(result);
    	Assert.assertTrue(anotherProducts.size() == 2);
    	
    	
    	LOG.info("Teste test02LoadListOfProductsOfTheCompany OK!");
    	
    }
    
}
