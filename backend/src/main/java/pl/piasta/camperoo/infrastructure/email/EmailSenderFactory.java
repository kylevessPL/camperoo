package pl.piasta.camperoo.infrastructure.email;

import lombok.experimental.UtilityClass;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@UtilityClass
class EmailSenderFactory {
    JavaMailSender create(Properties mailProperties) {
        return createJavaMailSender(mailProperties);
    }

    private JavaMailSender createJavaMailSender(Properties mailProperties) {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(mailProperties.getProperty("mail.port")));
        mailSender.setUsername(mailProperties.getProperty("mail.user"));
        mailSender.setPassword(mailProperties.getProperty("mail.password"));
        mailSender.setDefaultEncoding(StandardCharsets.UTF_8.name());
        mailSender.setJavaMailProperties(mailProperties);
        return mailSender;
    }
}
