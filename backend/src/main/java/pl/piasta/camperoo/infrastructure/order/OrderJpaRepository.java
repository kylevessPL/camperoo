package pl.piasta.camperoo.infrastructure.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.order.domain.Order;
import pl.piasta.camperoo.order.domain.OrderStatus;
import pl.piasta.camperoo.order.query.OrderBasicProjection;
import pl.piasta.camperoo.order.query.OrderProjection;
import pl.piasta.camperoo.payment.domain.PaymentStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    Page<IdProjection> findAllIdsBy(Pageable pageable);

    Page<IdProjection> findAllIdsByUserId(Long userId, Pageable pageable);

    @EntityGraph("orders-basic-graph")
    List<OrderBasicProjection> findAllByIdIn(Collection<Long> userIds, Sort sort);

    @EntityGraph("orders-graph")
    Optional<OrderProjection> findOneById(Long id);

    List<Order> findAllByStatusIsAndPaymentsStatusIs(OrderStatus orderStatus, PaymentStatus paymentStatus);

    boolean existsByIdAndUserId(Long orderId, Long userId);

    @Query("select o.user.id from Order o where o.invoice.id = :invoiceId")
    Long findUserIdByInvoiceId(Long invoiceId);
}
