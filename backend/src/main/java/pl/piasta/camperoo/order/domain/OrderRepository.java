package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.common.domain.Repository;

import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {
    Optional<Order> get(Long id);
}

