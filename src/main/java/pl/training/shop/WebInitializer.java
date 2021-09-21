package pl.training.shop;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class WebInitializer implements WebApplicationInitializer {

    private static final String BASE_PACKAGE = "pl.training.shop";

    @Override
    public void onStartup(ServletContext servletContext) {
        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.scan(BASE_PACKAGE);
        var dispatcherServlet = new DispatcherServlet(applicationContext);
        var dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }

}
