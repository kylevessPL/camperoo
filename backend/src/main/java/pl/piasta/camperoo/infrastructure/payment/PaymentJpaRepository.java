package pl.piasta.camperoo.infrastructure.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.payment.domain.Payment;
import pl.piasta.camperoo.payment.domain.PaymentStatus;
import pl.piasta.camperoo.payment.query.PaymentProjection;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Repository
public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Page<IdProjection> findAllIdsBy(Pageable pageable);

    Page<IdProjection> findAllIdsByOrderUserId(Long userId, Pageable pageable);

    @EntityGraph("payments-graph")
    List<PaymentProjection> findAllByIdIn(Collection<Long> ids, Sort sort);

    List<Payment> findAllByDeadlineBeforeAndStatusNot(Instant date, PaymentStatus status);
}
