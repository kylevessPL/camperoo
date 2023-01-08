package pl.piasta.camperoo.infrastructure.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.infrastructure.order.OrderJpaRepository;
import pl.piasta.camperoo.infrastructure.order.OrderStatusJpaRepository;
import pl.piasta.camperoo.infrastructure.payment.PaymentJpaRepository;
import pl.piasta.camperoo.infrastructure.payment.PaymentStatusJpaRepository;
import pl.piasta.camperoo.order.domain.OrderStatus;
import pl.piasta.camperoo.payment.domain.PaymentStatus;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
class OrderJobManager {
    private final OrderJpaRepository orderRepository;
    private final OrderStatusJpaRepository orderStatusRepository;
    private final PaymentJpaRepository paymentRepository;
    private final PaymentStatusJpaRepository paymentStatusRepository;

    @Transactional
    public void cancelExpiredPayments() {
        logger.info("Cancelling all expired payments");
        var status = paymentStatusRepository.getReferenceById(PaymentStatus.CANCELED);
        paymentRepository
                .findAllByDeadlineBeforeAndStatusNot(Instant.now(), status)
                .forEach(payment -> payment.updateStatus(status));
        logger.info("Completed cancellation of expired payments");
    }

    @Transactional
    public void cancelUnpaidOrders() {
        logger.info("Cleaning all orders with payments deadline exceeded");
        var placedOrderStatus = orderStatusRepository.getReferenceById(OrderStatus.PLACED);
        var cancelledOrderStatus = orderStatusRepository.getReferenceById(OrderStatus.CANCELED);
        var cancelledPaymentStatus = paymentStatusRepository.getReferenceById(PaymentStatus.CANCELED);
        orderRepository
                .findAllByStatusIsAndPaymentsStatusIs(placedOrderStatus, cancelledPaymentStatus)
                .forEach(order -> order.updateStatus(cancelledOrderStatus));
        logger.info("Complated cleanup of old orders");
    }
}

