package pl.piasta.camperoo.infrastructure.discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.discount.domain.Discount;

import java.util.Optional;

@Repository
public interface DiscountJpaRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findByCode(String code);
}
