package pl.piasta.camperoo.user.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class MissingPaymentException extends BusinessException {
    public MissingPaymentException(Long orderId) {
        super(
                "Order with id %s is is missing payment".formatted(orderId),
                ErrorCode.ORDER_MISSING_PAYMENT, ErrorProperty.ORDER_MISSING_PAYMENT, orderId
        );
    }
}
