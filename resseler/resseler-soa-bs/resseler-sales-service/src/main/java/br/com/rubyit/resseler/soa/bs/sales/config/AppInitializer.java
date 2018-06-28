package br.com.rubyit.resseler.soa.bs.sales.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Class AppInitializer
 * @author b11527
 *
 */
public class AppInitializer implements WebApplicationInitializer {

    /**
     * onStartup
     * @param servletContext
     * 
     */
    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");

        ServletRegistration.Dynamic dispatcherCXF = servletContext.addServlet(
                "cxf", new org.apache.cxf.transport.servlet.CXFServlet());
        dispatcherCXF.setLoadOnStartup(1);
        dispatcherCXF.addMapping("/services/*");
    }

    /**
     * getContext()
     * @return AnnotationConfigWebApplicationContext
     */
    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        String[] configs = new String[] {
                "br.com.rubyit.resseler.soa.bs.sales.config",
                "br.com.rubyit.resseler.core.persistence.repository.sales.config" };
        context.setConfigLocations(configs);

        return context;
    }

}
