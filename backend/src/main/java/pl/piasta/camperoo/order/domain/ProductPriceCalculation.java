package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.product.domain.Product;

import java.math.BigDecimal;

record ProductPriceCalculation(Product product, Integer quantity, BigDecimal totalPrice) {
}
