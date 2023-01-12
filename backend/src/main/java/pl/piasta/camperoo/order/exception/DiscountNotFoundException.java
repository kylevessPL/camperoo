package pl.piasta.camperoo.order.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.discount.domain.Discount;

public class DiscountNotFoundException extends NotFoundException {
    public DiscountNotFoundException(String code) {
        super(Discount.class, "code", code, ErrorCode.DISCOUNT_NOT_FOUND, ErrorProperty.DISCOUNT_NOT_FOUND);
    }
}
