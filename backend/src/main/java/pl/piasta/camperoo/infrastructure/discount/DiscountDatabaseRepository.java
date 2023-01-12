package pl.piasta.camperoo.infrastructure.discount;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.discount.domain.Discount;
import pl.piasta.camperoo.order.domain.OrderDiscountRepository;

import java.util.Optional;

@RequiredArgsConstructor
class DiscountDatabaseRepository implements OrderDiscountRepository {
    private final DiscountJpaRepository repository;

    @Override
    public Optional<Discount> getByCode(String code) {
        return repository.findByCode(code);
    }
}
