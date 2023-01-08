package pl.piasta.camperoo.infrastructure.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.payment.domain.PaymentStatus;

@Repository
public interface PaymentStatusJpaRepository extends JpaRepository<PaymentStatus, Long> {
}
