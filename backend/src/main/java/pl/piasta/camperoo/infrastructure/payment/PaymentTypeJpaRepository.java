package pl.piasta.camperoo.infrastructure.payment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.payment.domain.PaymentType;
import pl.piasta.camperoo.payment.query.PaymentTypeProjection;

import java.util.List;

@Repository
public interface PaymentTypeJpaRepository extends JpaRepository<PaymentType, Long> {
    @EntityGraph("payment-types-graph")
    List<PaymentTypeProjection> findAllBy();
}
