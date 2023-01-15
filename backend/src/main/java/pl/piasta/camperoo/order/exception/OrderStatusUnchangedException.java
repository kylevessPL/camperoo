package pl.piasta.camperoo.order.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class OrderStatusUnchangedException extends BusinessException {

    public OrderStatusUnchangedException(Long id, String statusCode) {
        super(
                "Status of order with id %d is unchanged".formatted(id),
                ErrorCode.ORDER_STATUS_UNCHANGED,
                ErrorProperty.ORDER_STATUS_UNCHANGED,
                id, statusCode
        );
    }
}
