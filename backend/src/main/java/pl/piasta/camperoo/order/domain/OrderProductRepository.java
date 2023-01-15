package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.product.domain.Product;

import java.util.List;
import java.util.Set;

public interface OrderProductRepository {
    List<Product> getAllByIdIn(Set<Long> ids);

    Product getTransportationProduct();
}

