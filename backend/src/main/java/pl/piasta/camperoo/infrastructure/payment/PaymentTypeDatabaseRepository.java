package pl.piasta.camperoo.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.payment.query.PaymentTypeProjection;
import pl.piasta.camperoo.payment.query.PaymentTypeQueryClient;

import java.util.List;

@RequiredArgsConstructor
class PaymentTypeDatabaseRepository implements PaymentTypeQueryClient {
    private final PaymentTypeJpaRepository repository;

    @Override
    public List<PaymentTypeProjection> findAllPaymentTypes() {
        return repository.findAllBy();
    }
}

