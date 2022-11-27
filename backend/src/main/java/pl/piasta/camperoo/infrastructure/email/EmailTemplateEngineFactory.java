package pl.piasta.camperoo.infrastructure.email;

import lombok.experimental.UtilityClass;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.StandardCharsets;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

@UtilityClass
class EmailTemplateEngineFactory {
    TemplateEngine create(String templatePath) {
        return createEmailTemplateEngine(templatePath);
    }

    private TemplateEngine createEmailTemplateEngine(String templatePath) {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(createHtmlTemplateResolver(templatePath));
        return templateEngine;
    }

    private ITemplateResolver createHtmlTemplateResolver(String templatePath) {
        var templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(templatePath);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        templateResolver.setCacheable(false);
        return templateResolver;
    }
}
