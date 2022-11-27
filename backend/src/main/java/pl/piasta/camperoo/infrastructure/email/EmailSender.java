package pl.piasta.camperoo.infrastructure.email;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import pl.piasta.camperoo.common.domain.vo.EmailAddress;

import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
class EmailSender {
    private final JavaMailSender mailSender;
    private final String senderAddress;

    void sendMail(EmailAddress recipient, String subject, String body) {
        sendMail(recipient, subject, body, null);
    }

    void sendMail(
            @NonNull EmailAddress recipient,
            @NonNull String subject,
            @NonNull String body,
            @Nullable Resource attachment
    ) {
        var message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        try {
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            messageHelper.setFrom(senderAddress);
            messageHelper.setTo(recipient.getEmail());
            if (nonNull(attachment)) {
                messageHelper.addAttachment(attachment.getFilename(), attachment);
            }
            mailSender.send(message);
        } catch (MessagingException | MailException ex) {
            logger.warn(ex.getMessage(), ex);
            throw new EmailDeliveryFailureException(recipient);
        }
    }
}

