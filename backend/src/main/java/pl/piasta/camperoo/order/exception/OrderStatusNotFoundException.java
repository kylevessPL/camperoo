package pl.piasta.camperoo.order.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.order.domain.OrderStatus;

public class OrderStatusNotFoundException extends NotFoundException {
    public OrderStatusNotFoundException(Long id) {
        super(OrderStatus.class, "id", id, ErrorCode.ORDER_STATUS_NOT_FOUND, ErrorProperty.ORDER_STATUS_NOT_FOUND);
    }
}
