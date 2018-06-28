package br.com.rubyit.resseler.commons.core.salesman.test;

import static br.com.rubyit.resseler.kernel.ResselerSystemProperties.IMAGES_PRODUCTS_PATH;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Email;
import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.Price;
import br.com.rubyit.resseler.commons.core.ProductAttributes;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.core.infra.Image;
import br.com.rubyit.resseler.commons.core.infra.Images;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.commons.kernel.dto.Stock;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.kernel.ResselerSystemProperties;

public class SalesmanTests {
	
	@Test
	public void testPutProductsOnSalesmanStock() {
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
    	
    	Brand marcaAvon = new Brand();
    	List<ProductDTO> produtosDaMarca = new ArrayList<>();
    	produtosDaMarca.add(batonAvon);
    	marcaAvon.setName("Avon");
    	
    	ModelBrand modeloBatomAvon = new ModelBrand();
    	modeloBatomAvon.setName("Ultra Color");
    	modeloBatomAvon.setCode("Ultramatte");
    	
    	ProductAttributes attributosDoBatomAvon = new ProductAttributes();
    	Images images24x36 = new Images();
    	Image image = new Image();
    	String fileSystem = ResselerSystemProperties.retrieveBaseFileSystem(); 
    	image.setPath(fileSystem + IMAGES_PRODUCTS_PATH + "/batom-ultra-color-ultramatte-fps15-pessego-avn2680.jpg");
    	images24x36.retrieveImages().add(image);
    	attributosDoBatomAvon.putAttribute("images24x36", images24x36);
    	attributosDoBatomAvon.putAttribute("FPS", "15");
    	attributosDoBatomAvon.putAttribute("weight", "3.6g");
    	
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
	}
    
    private Category createTestProductCategoryTree() {
    	Long idCategoriaProdutosParaOCorpoHumano = 1L;
    	Long rootCategoryId = idCategoriaProdutosParaOCorpoHumano.longValue();
    	Long parentCategoryId = null;
    	Boolean isRootCategory = true;
    	Boolean hasSubCategories = true;
    	String nomeCategoria = "Corpo Humano";
    	String descricaoCategoria = "Categoria raiz de produtos para o corpo humano.";
    	Category categoriaProdutosParaOCorpoHumano = new Category(idCategoriaProdutosParaOCorpoHumano);
    	categoriaProdutosParaOCorpoHumano.setRootCategoryID(rootCategoryId);
    	categoriaProdutosParaOCorpoHumano.setParentCategoryID(parentCategoryId);
    	categoriaProdutosParaOCorpoHumano.setRootCategory(isRootCategory);
    	categoriaProdutosParaOCorpoHumano.setHasSubCategories(hasSubCategories);
    	categoriaProdutosParaOCorpoHumano.setName(nomeCategoria);
    	categoriaProdutosParaOCorpoHumano.setDescription(descricaoCategoria);
    	
    	Long idCategoriaProdutosMaquiagem = 11L;
    	rootCategoryId = rootCategoryId.longValue();
    	parentCategoryId = rootCategoryId;
    	isRootCategory = false;
    	hasSubCategories = true;
    	nomeCategoria = "Maquiagem";
    	descricaoCategoria = "SubCategoria de produtos de maquiagem.";
    	Category categoriaProdutosMaquiagem = new Category(idCategoriaProdutosMaquiagem);
    	categoriaProdutosMaquiagem.setRootCategoryID(rootCategoryId);
    	categoriaProdutosMaquiagem.setParentCategoryID(parentCategoryId);
    	categoriaProdutosMaquiagem.setRootCategory(isRootCategory);
    	categoriaProdutosMaquiagem.setHasSubCategories(hasSubCategories);
    	categoriaProdutosMaquiagem.setName(nomeCategoria);
    	categoriaProdutosMaquiagem.setDescription(descricaoCategoria);
    	
    	Long idCategoriaProdutosBoca = 111L;
    	rootCategoryId = rootCategoryId.longValue();
    	parentCategoryId = idCategoriaProdutosMaquiagem;
    	isRootCategory = false;
    	hasSubCategories = true;
    	nomeCategoria = "Boca";
    	descricaoCategoria = "SubCategoria de produtos para a Boca.";
    	Category categoriaProdutosBoca = new Category(idCategoriaProdutosBoca);
    	categoriaProdutosBoca.setRootCategoryID(rootCategoryId);
    	categoriaProdutosBoca.setParentCategoryID(parentCategoryId);
    	categoriaProdutosBoca.setRootCategory(isRootCategory);
    	categoriaProdutosBoca.setHasSubCategories(hasSubCategories);
    	categoriaProdutosBoca.setName(nomeCategoria);
    	categoriaProdutosBoca.setDescription(descricaoCategoria);
    	
    	Long idCategoriaProdutosBatom = 1111L;
    	rootCategoryId = rootCategoryId.longValue();
    	parentCategoryId = idCategoriaProdutosBoca;
    	isRootCategory = false;
    	hasSubCategories = false;
    	nomeCategoria = "Batom";
    	descricaoCategoria = "SubCategoria de produtos Batom.";
    	Category categoriaProdutosBatom = new Category(idCategoriaProdutosBatom);
    	categoriaProdutosBatom.setRootCategoryID(rootCategoryId);
    	categoriaProdutosBatom.setParentCategoryID(parentCategoryId);
    	categoriaProdutosBatom.setRootCategory(isRootCategory);
    	categoriaProdutosBatom.setHasSubCategories(hasSubCategories);
    	categoriaProdutosBatom.setName(nomeCategoria);
    	categoriaProdutosBatom.setDescription(descricaoCategoria);
    	
    	return categoriaProdutosBatom;
    }
}

