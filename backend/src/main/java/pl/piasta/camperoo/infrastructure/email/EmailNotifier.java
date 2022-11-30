package pl.piasta.camperoo.infrastructure.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.security.domain.AuthenticationEmailNotifier;
import pl.piasta.camperoo.user.domain.User;

@Slf4j
@RequiredArgsConstructor
class EmailNotifier implements AuthenticationEmailNotifier {
    private final EmailSender emailSender;
    private final TemplateEngine emailTemplateEngine;
    private final String frontendUrl;

    @Override
//    @Async
    public void sendPasswordResetToken(VerificationTokenCode token, User user) {
        logger.info("Sending password reset email for token: " + token);
        var context = new Context();
        context.setVariable("passwordTokenCode", token);
        context.setVariable("accountName", user.getPerson().getFirstName());
        context.setVariable("frontendUrl", frontendUrl);
        emailSender.sendMail(
                user.getEmail(),
                "Camperoo – password reset",
                emailTemplateEngine.process("password-reset-email", context)
        );
    }
}
