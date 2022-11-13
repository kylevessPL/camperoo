package pl.piasta.camperoo;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.NonNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pl.piasta.camperoo.common.util.AppProfiles;

import javax.servlet.ServletContext;

import static java.util.Objects.nonNull;

public class CamperooApp implements WebApplicationInitializer {

    private static final String ACTIVE_PROFILES_PROPERTY = "PROFILES_ACTIVE";

    @Override
    public void onStartup(@NonNull ServletContext servletContext) {
        var applicationContext = createApplicationContext();
        servletContext.addListener(new ContextLoaderListener(applicationContext));
        var servlet = servletContext.addServlet("dispatcher", createDispatcher(applicationContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

    private AnnotationConfigWebApplicationContext createApplicationContext() {
        var applicationContext = new AnnotationConfigWebApplicationContext();
        setupProfiles(applicationContext.getEnvironment());
        applicationContext.setConfigLocation("pl.piasta.camperoo");
        return applicationContext;
    }

    private static DispatcherServlet createDispatcher(AnnotationConfigWebApplicationContext applicationContext) {
        var dispatcher = new DispatcherServlet(applicationContext);
        dispatcher.setThrowExceptionIfNoHandlerFound(true);
        return dispatcher;
    }

    private void setupProfiles(ConfigurableEnvironment environment) {
        var profiles = environment.getProperty(ACTIVE_PROFILES_PROPERTY);
        environment.setActiveProfiles(nonNull(profiles) ? profiles : AppProfiles.LOCAL);
    }
}
