package pl.piasta.camperoo.infrastructure.order;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.domain.OrderStatus;
import pl.piasta.camperoo.order.domain.OrderStatusRepository;

import java.util.Optional;

@RequiredArgsConstructor
class OrderStatusDatabaseRepository implements OrderStatusRepository {
    private final OrderStatusJpaRepository repository;

    @Override
    public Optional<OrderStatus> getByCode(String code) {
        return repository.findByCode(code);
    }
}

