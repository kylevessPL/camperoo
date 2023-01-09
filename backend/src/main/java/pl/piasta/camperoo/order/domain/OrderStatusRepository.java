package pl.piasta.camperoo.order.domain;

import java.util.Optional;

public interface OrderStatusRepository {
    Optional<OrderStatus> getByCode(String code);
}

