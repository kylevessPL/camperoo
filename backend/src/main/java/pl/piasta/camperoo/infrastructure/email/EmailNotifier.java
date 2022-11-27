package pl.piasta.camperoo.infrastructure.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

@RequiredArgsConstructor
class EmailNotifier {
    private final JavaMailSender emailSender;
    private final TemplateEngine emailTemplateEngine;
    private final String frontendUrl;
    private final String senderEmail;
}
