package pl.piasta.camperoo.infrastructure.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.common.util.PageBuilder;
import pl.piasta.camperoo.order.domain.Order;
import pl.piasta.camperoo.order.domain.OrderRepository;
import pl.piasta.camperoo.order.exception.OrderNotFoundException;
import pl.piasta.camperoo.order.query.OrderBasicProjection;
import pl.piasta.camperoo.order.query.OrderProjection;
import pl.piasta.camperoo.order.query.OrderQueryClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class OrderDatabaseRepository implements OrderRepository, OrderQueryClient {
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

    @Override
    public Optional<Order> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<OrderBasicProjection> findAllOrders(Pageable pageable) {
        var page = repository.findAllIdsBy(pageable);
        var ids = IdProjection.retrieveAllIds(page.toList());
        var content = repository.findAllByIdIn(ids, pageable.getSort());
        return PageBuilder.fromContent(content)
                .page(page)
                .build();
    }

    @Override
    public Page<OrderBasicProjection> findOrdersByUserId(Long userId, Pageable pageable) {
        var page = repository.findAllIdsByUserId(userId, pageable);
        var ids = IdProjection.retrieveAllIds(page.toList());
        var content = repository.findAllByIdIn(ids, pageable.getSort());
        return PageBuilder.fromContent(content)
                .page(page)
                .build();
    }

    @Override
    public OrderProjection findOrderById(Long orderId) {
        return repository
                .findOneById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}

