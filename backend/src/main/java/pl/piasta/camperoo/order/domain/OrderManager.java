package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.exception.OrderNotFoundException;
import pl.piasta.camperoo.order.exception.OrderStatusNotFoundException;

@RequiredArgsConstructor
class OrderManager {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;

    void updateOrderStatus(Long orderId, String statusCode) {
        var status = orderStatusRepository
                .getByCode(statusCode)
                .orElseThrow(() -> new OrderStatusNotFoundException(statusCode));
        orderRepository.get(orderId).ifPresentOrElse(
                user -> user.updateStatus(status),
                () -> {throw new OrderNotFoundException(orderId);}
        );
    }
}
