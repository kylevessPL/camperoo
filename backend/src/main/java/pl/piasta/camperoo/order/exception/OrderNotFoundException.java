package pl.piasta.camperoo.order.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.order.domain.Order;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(Long id) {
        super(Order.class, "id", id, ErrorCode.ORDER_NOT_FOUND, ErrorProperty.ORDER_NOT_FOUND);
    }
}
