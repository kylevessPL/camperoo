package pl.piasta.camperoo.order.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.delivery.domain.DeliveryType;

public class DeliveryTypeNotFoundException extends NotFoundException {
    public DeliveryTypeNotFoundException(Long id) {
        super(DeliveryType.class, "id", id, ErrorCode.DELIVERY_TYPE_NOT_FOUND, ErrorProperty.DELIVERY_TYPE_NOT_FOUND);
    }
}
