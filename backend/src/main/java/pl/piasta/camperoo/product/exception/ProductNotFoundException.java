package pl.piasta.camperoo.product.exception;

import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.product.domain.Product;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super(Product.class, "id", id, ErrorCode.PRODUCT_NOT_FOUND, ErrorProperty.PRODUCT_NOT_FOUND);
    }
}
