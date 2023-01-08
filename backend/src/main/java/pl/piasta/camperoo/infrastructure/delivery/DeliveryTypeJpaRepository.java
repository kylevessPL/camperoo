package pl.piasta.camperoo.infrastructure.delivery;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.delivery.domain.DeliveryType;

import java.util.List;

@Repository
public interface DeliveryTypeJpaRepository extends JpaRepository<DeliveryType, Long> {
    @EntityGraph("delivery-types-graph")
    List<ConstantProjection> findAllBy();
}
