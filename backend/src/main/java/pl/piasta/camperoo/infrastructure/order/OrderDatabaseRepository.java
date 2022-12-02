package pl.piasta.camperoo.infrastructure.order;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.domain.Order;
import pl.piasta.camperoo.order.domain.OrderRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class OrderDatabaseRepository implements OrderRepository {
    private final OrderJpaRepository repository;

    @Override
    public Order save(Order entity) {
        return repository.save(entity);
    }

    @Override
    public Order getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<Order> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> getAll() {
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

