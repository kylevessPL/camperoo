package pl.piasta.camperoo.order.exception;

import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class ProductNotAvailableException extends BusinessException {
    public ProductNotAvailableException(Long id, Integer quantity) {
        super(
                "Product with id = %d is not available in requested quantity".formatted(id),
                ErrorCode.PRODUCT_NOT_AVAILABLE,
                ErrorProperty.PRODUCT_NOT_AVAILABLE,
                id, quantity
        );
    }
}
