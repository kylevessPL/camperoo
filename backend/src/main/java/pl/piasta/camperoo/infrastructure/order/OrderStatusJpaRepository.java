package pl.piasta.camperoo.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.order.domain.OrderStatus;

import java.util.Optional;

@Repository
public interface OrderStatusJpaRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByCode(String code);
}
