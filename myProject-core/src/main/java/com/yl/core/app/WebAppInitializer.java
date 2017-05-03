package com.yl.core.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;

/**
 * @author Richard Xue
 * @version 1.0
 * @date 03/12/2016
 * @description Servlet 3.0 to replace web.xml
 */
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebAppConfig.class,AppDataConfig.class);
//        XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
//        xmlWebApplicationContext.setConfigLocation("classpath*:spring-*.xml");

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//        dispatcherServlet.register(WebMvcConfig.class);

//        rootContext.getEnvironment().setActiveProfiles("openshift");

        // Register and map the dispatcher servlet
//        ServletRegistration.Dynamic dispatcher = container.addServlet("crm", new DispatcherServlet(dispatcherServlet));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.setAsyncSupported(true);
////        dispatcher.addMapping("/");
//        dispatcher.addMapping("/api/*");
//        registerFilter(container);
    }

    private void registerFilter(ServletContext context) {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setForceEncoding(true);
        cef.setEncoding("UTF-8");
//        context.addFilter("ipFilter",new IpFilter()).addMappingForUrlPatterns(null,true,"/*");
        context.addFilter("encodingFilter", cef).addMappingForUrlPatterns(null ,true, "/*");
        //context.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");

//        context.addFilter("IpFilter", IpFilter.class).addMappingForUrlPatterns(null,true,"/*");
    }

}
