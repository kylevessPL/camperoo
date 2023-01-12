package pl.piasta.camperoo.infrastructure.order;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.domain.OrderStatus;
import pl.piasta.camperoo.order.domain.OrderStatusRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class OrderStatusDatabaseRepository implements OrderStatusRepository {
    private final OrderStatusJpaRepository repository;

    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        return repository.save(orderStatus);
    }

    @Override
    public OrderStatus getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<OrderStatus> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<OrderStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}

