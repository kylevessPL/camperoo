package pl.piasta.camperoo.infrastructure.email;

import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.common.util.PropertiesLoader;

@Configuration
class EmailConfiguration {
    private static final String MAIL_PROPERTIES = "mail-config.yml";

    private EmailNotifier emailNotifier() {
        var mailProperties = PropertiesLoader.EAGER.load(MAIL_PROPERTIES);
        var emailSender = EmailSenderFactory.create(mailProperties);
        var emailTemplateEngine = EmailTemplateEngineFactory.create("/templates/");
        return new EmailNotifier(emailSender, emailTemplateEngine);
    }
}
