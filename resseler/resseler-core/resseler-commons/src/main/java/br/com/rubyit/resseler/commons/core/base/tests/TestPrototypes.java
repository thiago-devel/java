package br.com.rubyit.resseler.commons.core.base.tests;

import static br.com.rubyit.resseler.kernel.ResselerSystemProperties.IMAGES_PRODUCTS_PATH;

import java.math.BigDecimal;

import org.apache.commons.id.serial.LongGenerator;

import br.com.rubyit.resseler.commons.core.Brand;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.Price;
import br.com.rubyit.resseler.commons.core.ProductAttributes;
import br.com.rubyit.resseler.commons.core.categories.Category;
import br.com.rubyit.resseler.commons.core.infra.Image;
import br.com.rubyit.resseler.commons.core.infra.Images;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.kernel.ResselerSystemProperties;

public abstract class TestPrototypes {
	public final static LongGenerator longGenerator = new LongGenerator(false, 1L);

	public Category createTestProductCategoryTree() {
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

	public ProductDTO createTestProductBatonAvon(ModelBrand modeloBatomAvon) {
		ProductDTO batonAvon = new ProductDTO();
		batonAvon.setID(longGenerator.nextLongIdentifier());
		batonAvon.setName("Batom Ultramatte");
		batonAvon.setDescription("Batom Matte Ultra Color Ultramatte FPS 15 3,6g");
		batonAvon
				.appendProductDetailDescription("O Batom Ultramatte da Ultra Color possui acabamento matte aveludado,");
		batonAvon.appendProductDetailDescription(
				"oferecendo cobertura uniforme e sem brilho. Oferece hidratação garantida, ");
		batonAvon.appendProductDetailDescription("não deixa seus lábios ressecados e desliza suavemente durante a ");
		batonAvon.appendProductDetailDescription(
				"aplicação. Além disso, tem proteção solar FPS 15 e conta com 10 opções de ");
		batonAvon.appendProductDetailDescription(
				"cores intensas e que duram horas. Sinta-se confortável para ser você, ");
		batonAvon.appendProductDetailDescription("escolha sua cor favorita e experimente!");

		Price batonAvonPrice = new Price();
		batonAvonPrice.putArbitraryPrice(BigDecimal.valueOf(21, 99));
		batonAvon.setProductPrice(batonAvonPrice);
		batonAvon.setModelBrand(modeloBatomAvon);

		return batonAvon;
	}

	public ProductDTO createTestProductBatomQualquer(ModelBrand modeloBatomAvon) {
		ProductDTO batonAvon = new ProductDTO();
		batonAvon.setID(longGenerator.nextLongIdentifier());
		batonAvon.setName("Batom Qualquer");
		batonAvon.setDescription("Batom Qualquer 3,6g");
		batonAvon.appendProductDetailDescription("Um batom Qualquer!");

		Price batonAvonPrice = new Price();
		batonAvonPrice.putArbitraryPrice(BigDecimal.valueOf(1, 99));
		batonAvon.setProductPrice(batonAvonPrice);
		batonAvon.setModelBrand(modeloBatomAvon);

		return batonAvon;
	}

	public ProductDTO createTestProductBatomBoticarioIntense(ModelBrand modeloBatomAvon) {
		ProductDTO batonAvon = new ProductDTO();
		batonAvon.setID(longGenerator.nextLongIdentifier());
		batonAvon.setName("Intense Batom");
		batonAvon.setDescription("Intense Batom Longa Duração FPS 20 3,6g");
		batonAvon.appendProductDetailDescription(
				"Com toque aveludado, cor intensa e longa durabilidade, o Intense Batom");
		batonAvon.appendProductDetailDescription(" Longa Duração vem em 7 cores.");
		batonAvon.appendProductDetailDescription(
				"Com duração de até 6 horas, o batom intense possui FPS 20 e alta cobertura");
		batonAvon.appendProductDetailDescription(
				" com acabamento uniforme. Sua fórmula com manteiga de Karité e Vitamina E ");
		batonAvon.appendProductDetailDescription(
				"garante hidratação de até 24 horas, mantendo os lábios hidratados e bem ");
		batonAvon.appendProductDetailDescription("cuidados ao longo do dia.");

		Price batonAvonPrice = new Price();
		batonAvonPrice.putArbitraryPrice(BigDecimal.valueOf(21, 90));
		batonAvon.setProductPrice(batonAvonPrice);
		batonAvon.setModelBrand(modeloBatomAvon);

		return batonAvon;
	}

	public ProductDTO createTestProduct02(ModelBrand modeloBatomAvon) {
		ProductDTO batonAvon = new ProductDTO();
		batonAvon.setID(longGenerator.nextLongIdentifier());
		batonAvon.setName("Urban Ballet Esmalte");
		batonAvon.setDescription("Make B. Urban Ballet Esmalte Salmon Way");
		batonAvon.appendProductDetailDescription(
				"O Make B. Urban Ballet Esmalte Salmon Way tem fácil aplicação, secagem");
		batonAvon.appendProductDetailDescription(" rápida e dura mais.");
		batonAvon.appendProductDetailDescription("Sua textura leve evita a formação de bolinhas, tem alta cobertura e");
		batonAvon.appendProductDetailDescription(" brilho intenso que garantem acabamento impecável.");

		Price batonAvonPrice = new Price();
		batonAvonPrice.putArbitraryPrice(BigDecimal.valueOf(17, 99));
		batonAvon.setProductPrice(batonAvonPrice);
		batonAvon.setModelBrand(modeloBatomAvon);

		return batonAvon;
	}

	public ModelBrand createTestModelBrand() {
		ModelBrand modeloBatomAvon = new ModelBrand();
		modeloBatomAvon.setName("Ultra Color");
		modeloBatomAvon.setCode("Ultramatte");

		return modeloBatomAvon;
	}

	public ModelBrand createTestModelBrand01() {
		ModelBrand modeloBatomAvon = new ModelBrand();
		modeloBatomAvon.setName("Intense");
		modeloBatomAvon.setCode("Intense");

		return modeloBatomAvon;
	}

	public Brand createTestBrand() {
		Brand brandAvon = new Brand();
		brandAvon.setID(longGenerator.nextLongIdentifier());
		brandAvon.setName("BATONS AVON");
		return brandAvon;
	}

	public Brand createTestBrand01() {
		Brand brandAvon = new Brand();
		brandAvon.setID(longGenerator.nextLongIdentifier());
		brandAvon.setName("BATONS BOTICÁRIO");
		return brandAvon;
	}

	public Company createTestCompany() {
		Company companyAvon = new Company();
		companyAvon.setID(longGenerator.nextLongIdentifier());
		companyAvon.setName("AVON");
		return companyAvon;
	}

	public Company createTestCompanyBoticario() {
		Company companyAvon = new Company();
		companyAvon.setID(longGenerator.nextLongIdentifier());
		companyAvon.setName("BOTACÁRIO");
		return companyAvon;
	}

	public ProductAttributes createTestAttributes() {
		ProductAttributes attributosDoBatomAvon = new ProductAttributes();
		attributosDoBatomAvon.setID(longGenerator.nextLongIdentifier());
		Images images24x36 = new Images();
		Image image = new Image();
		String fileSystem = ResselerSystemProperties.retrieveBaseFileSystem();
		image.setPath(fileSystem + IMAGES_PRODUCTS_PATH + "/batom-ultra-color-ultramatte-fps15-pessego-avn2680.jpg");
		images24x36.retrieveImages().add(image);
		attributosDoBatomAvon.putAttribute("images24x36", images24x36);
		attributosDoBatomAvon.putAttribute("FPS", "15");
		attributosDoBatomAvon.putAttribute("weight", "3.6g");

		return attributosDoBatomAvon;
	}

	public ProductAttributes createTestAttributes01() {
		ProductAttributes attributosDoBatomAvon = new ProductAttributes();
		attributosDoBatomAvon.setID(longGenerator.nextLongIdentifier());
		Images images500x500 = new Images();
		Image image = new Image();
		String fileSystem = ResselerSystemProperties.retrieveBaseFileSystem();
		image.setPath(fileSystem + IMAGES_PRODUCTS_PATH + "/intense-batom-longa-duracao-150-vontades-27182.jpg");
		images500x500.retrieveImages().add(image);
		attributosDoBatomAvon.putAttribute("images500x500", images500x500);
		attributosDoBatomAvon.putAttribute("FPS", "20");
		attributosDoBatomAvon.putAttribute("weight", "3.6g");

		return attributosDoBatomAvon;
	}
}
