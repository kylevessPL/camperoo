package pl.piasta.camperoo.infrastructure.email;

import pl.piasta.camperoo.common.domain.vo.EmailAddress;
import pl.piasta.camperoo.common.exception.CriticalException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

class EmailDeliveryFailureException extends CriticalException {
    public EmailDeliveryFailureException(EmailAddress emailAddress) {
        super(
                "Failed to deliver email to " + emailAddress.getEmail(),
                ErrorCode.CRITICAL,
                ErrorProperty.EMAIL_DELIVERY_ERROR,
                emailAddress.getEmail()
        );
    }
}
