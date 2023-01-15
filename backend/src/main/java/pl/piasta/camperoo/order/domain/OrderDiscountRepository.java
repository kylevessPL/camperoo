package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.discount.domain.Discount;

import java.util.Optional;

public interface OrderDiscountRepository {
    Optional<Discount> getByCode(String code);
}

