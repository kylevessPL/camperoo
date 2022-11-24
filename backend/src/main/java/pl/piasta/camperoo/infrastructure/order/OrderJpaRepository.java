package pl.piasta.camperoo.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.order.domain.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
