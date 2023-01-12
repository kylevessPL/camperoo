package pl.piasta.camperoo.infrastructure.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.piasta.camperoo.common.domain.vo.VerificationTokenCode;
import pl.piasta.camperoo.order.domain.Order;
import pl.piasta.camperoo.order.domain.OrderEmailNotifier;
import pl.piasta.camperoo.security.domain.AuthenticationEmailNotifier;
import pl.piasta.camperoo.user.domain.User;
import pl.piasta.camperoo.user.domain.UserEmailNotifier;

import java.util.Locale;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
class EmailNotifier implements AuthenticationEmailNotifier, UserEmailNotifier, OrderEmailNotifier {
    private static final String PASSWORD_RESET_TEMPLATE = "password-reset-email";
    private static final String ACCOUNT_CREATION_TEMPLATE = "account-creation-email";
    private static final String ORDER_INVOICE_TEMPLATE = "order-invoice-email";
    private static final String ORDER_STATUS_CHANGE_TEMPLATE = "order-status-change-email";

    private final EmailSender emailSender;
    private final TemplateEngine emailTemplateEngine;
    private final String frontendUrl;

    @Override
    public void sendPasswordResetToken(VerificationTokenCode token, User user) {
        logger.info("Sending password reset email for token: " + token);
        var context = new Context();
        context.setVariables(Map.of(
                "passwordResetTokenCode", token,
                "accountName", user.getPerson().getFirstName(),
                "frontendUrl", frontendUrl
        ));
        emailSender.sendMail(
                user.getEmail(),
                "Camperoo – password reset",
                emailTemplateEngine.process(PASSWORD_RESET_TEMPLATE, context)
        );
    }

    @Override
    public void sendAccountCreationToken(VerificationTokenCode token, User user) {
        logger.info("Sending account creation email for token: " + token);
        var context = new Context();
        context.setVariables(Map.of(
                "accountCreationTokenCode", token,
                "accountName", user.getPerson().getFirstName(),
                "frontendUrl", frontendUrl
        ));
        emailSender.sendMail(
                user.getEmail(),
                "Camperoo – account created",
                emailTemplateEngine.process(ACCOUNT_CREATION_TEMPLATE, context)
        );
    }

    @Override
    public void sendOrderInvoiceGeneratedEmail(Order order) {
        logger.info("Sending order invoice email for order: " + order.getId());
        var context = new Context();
        context.setVariables(Map.of(
                "orderNumber", order.getId(),
                "accountName", order.getUser().getPerson().getFirstName(),
                "frontendUrl", frontendUrl
        ));
        emailSender.sendMail(
                order.getUser().getEmail(),
                "Camperoo – order #%d invoice".formatted(order.getId()),
                emailTemplateEngine.process(ORDER_INVOICE_TEMPLATE, context)
        );
    }

    @Override
    public void sendOrderStatusChangeToken(Order order) {
        logger.info("Sending order status change email for order: " + order.getId());
        var context = new Context();
        context.setVariables(Map.of(
                "orderNumber", order.getId(),
                "orderStatus", order.getStatus().getLocalizedName(Locale.ENGLISH),
                "accountName", order.getUser().getPerson().getFirstName(),
                "frontendUrl", frontendUrl
        ));
        emailSender.sendMail(
                order.getUser().getEmail(),
                "Camperoo – order #%d status changed".formatted(order.getId()),
                emailTemplateEngine.process(ORDER_STATUS_CHANGE_TEMPLATE, context)
        );
    }
}
