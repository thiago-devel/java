package br.com.rubyit.resseler.soa.bs.sales.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import br.com.rubyit.resseler.soa.bs.sales.ProductsAndCategoriesBS;

@Configuration
@ImportResource(value = { "classpath:META-INF/cxf/cxf.xml",
        "classpath:META-INF/cxf/cxf-servlet.xml" })
@ComponentScan("br.com.rubyit.resseler.soa.bs.sales")
public class CXFConfig {
	
    @Autowired
    Bus cxfBus;

    /**
     * ButtonUP
     * @return
     */
    @Bean
    public Endpoint jaxWsProductsAndCategoriesBSServer() {
        EndpointImpl endpoint = new EndpointImpl(cxfBus, new ProductsAndCategoriesBS());
        endpoint.setAddress("/ProductAndCategoriesBS");
        endpoint.publish();
        
        return endpoint;
    }

    /**
     * TopDown
     * @return Server
     * 
     */
    @Bean
    public Server jaxWsSalesBSServer() {
    	final JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
    	factory.setServiceBean(new ProductsAndCategoriesBS());
    	factory.setAddress("/ProductsAndCategoriesBS");
    	factory.setServiceName(
    			new QName("http://resseler.rubyit.com.br/", "ProductsAndCategoriesBS"));
    	
    	final Map<String, Object> properties = new HashMap<>();
    	properties.put("schema-validation-enabled", true);
    	
    	factory.setProperties(properties);
    	
    	return factory.create();
    }
    /*
    @Bean
    public Server jaxWsSalesBSServer() {
        final JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceBean(new SalesBS());
        factory.setAddress("/SalesBS");
        factory.setServiceName(
                new QName("http://resseler.rubyit.com.br/", "SalesBS"));

        final Map<String, Object> properties = new HashMap<>();
        properties.put("schema-validation-enabled", true);

        factory.setProperties(properties);

        return factory.create();
    }
    */
}
