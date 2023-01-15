package pl.piasta.camperoo.order.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.payment.domain.PaymentType;

public class PaymentTypeNotFoundException extends NotFoundException {
    public PaymentTypeNotFoundException(Long id) {
        super(PaymentType.class, "id", id, ErrorCode.PAYMENT_TYPE_NOT_FOUND, ErrorProperty.PAYMENT_TYPE_NOT_FOUND);
    }
}
