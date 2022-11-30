package pl.piasta.camperoo.infrastructure.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.common.util.PropertiesLoader;

@Configuration
class EmailConfiguration {
    private static final String MAIL_PROPERTIES = "mail-config.yml";

    @Bean
    EmailNotifier emailNotifier(
            @Value("${app.frontendUrl}") String frontendUrl,
            @Value("${app.email.sender}") String senderEmail
    ) {
        var mailProperties = PropertiesLoader.EAGER.load(MAIL_PROPERTIES);
        var mailSender = MailSenderFactory.create(mailProperties);
        var emailTemplateEngine = EmailTemplateEngineFactory.create("/templates/");
        var emailSender = new EmailSender(mailSender, senderEmail);
        return new EmailNotifier(emailSender, emailTemplateEngine, frontendUrl);
    }
}
