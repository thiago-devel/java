package br.com.rubyit.resseler.soa.bs.sales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.resseler.directsales.commons.persistence.dao.impl.CapitalSeriesDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.InsuredDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.ProductInsuranceDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SalesmanDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.UserDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.CardPaymentDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.InstallmentDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.InsuredObjectDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDBDAO;
import br.com.resseler.directsales.sales.repository.RepositorySales;
import br.com.rubyit.resseler.core.business.CertificateBusiness;
import br.com.rubyit.resseler.core.business.facade.SaleFacade;
import br.com.rubyit.resseler.core.business.sale.impl.SaleBusinessImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "br.com.rubyit.resseler.soa.bs.sales",
        "br.com.rubyit.resseler.core.persistence",
        "br.com.rubyit.resseler.core.business.facade",
        "br.com.rubyit.resseler.core.business.sale.impl",
        "br.com.rubyit.resseler.commons.persistence.dao.config" })
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class BSAppConfig extends WebMvcConfigurerAdapter {

    /**
     * Cria uma instancia
     * SaleFacade
     * @return SaleFacade
     */
    @Bean
    public SaleFacade saleFacade() {
        return new SaleFacade();
    }

    /**
     * Cria uma instancia 
     * SaleBusinessImpl
     * @return SaleBusinessImpl
     */
    @Bean
    public SaleBusinessImpl saleBusinessImpl() {
        return new SaleBusinessImpl();
    }

    /**
     * Cria uma instancia 
     * CertificateBusiness
     * @return CertificateBusiness
     */
    @Bean
    public CertificateBusiness certificateBusiness() {
        return new CertificateBusiness();
    }

    /**
     * Cria uma instancia 
     * RepositorySales
     * @return RepositorySales
     */
    @Bean
    public RepositorySales repositorySales() {
        return new RepositorySales();
    }

    /**
     * Cria uma instancia 
     * SalesDAO
     * @return SalesDAO
     */
    @Bean
    public SalesDAO salesDAO() {
        return new SalesDAO();
    }

    /**
     * Cria uma instancia 
     * SalesDBDAO
     * @return SalesDBDAO
     */
    @Bean
    public SalesDBDAO salesDBDAO() {
        return new SalesDBDAO();
    }

/*
    @Bean
    public CertificateDAO certificateDAO() {
        return new CertificateDAO();
    }*/

    /**
     * Cria uma instancia 
     * ProductInsuranceDAO
     * @return ProductInsuranceDAO
     */
    @Bean
    public ProductInsuranceDAO productInsuranceDAO() {
        return new ProductInsuranceDAO();
    }

    /**
     * Cria uma instancia 
     * CapitalSeriesDAO
     * @return CapitalSeriesDAO
     */
    @Bean
    public CapitalSeriesDAO capitalSeriesDAO() {
        return new CapitalSeriesDAO();
    }

    /**
     * Cria uma instancia 
     * UserDAO
     * @return UserDAO
     */
    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    /**
     * Cria uma instancia 
     * InsuredDAO
     * @return InsuredDAO
     */
    @Bean
    public InsuredDAO insuredDAO() {
        return new InsuredDAO();
    }

    /**
     * Cria uma instancia 
     * CardPaymentDAO
     * @return CardPaymentDAO
     */
    @Bean
    public CardPaymentDAO cardPaymentDAO() {
        return new CardPaymentDAO();
    }

    /**
     * Cria uma instancia 
     * InstallmentDAO
     * @return InstallmentDAO
     */
    @Bean
    public InstallmentDAO installmentDAO() {
        return new InstallmentDAO();
    }

    /**
     * Cria uma instancia 
     * InsuredObjectDAO
     * @return InsuredObjectDAO
     */
    @Bean
    public InsuredObjectDAO insuredObjectDAO() {
        return new InsuredObjectDAO();
    }

    /**
     * Cria uma instancia
     * SalesmanDAO
     * @return SalesmanDAO
     */
    @Bean
    public SalesmanDAO salesmanDAO() {
        return new SalesmanDAO();
    }
}
