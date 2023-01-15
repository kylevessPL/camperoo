package pl.piasta.camperoo.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.domain.OrderPaymentTypeRepository;
import pl.piasta.camperoo.payment.domain.PaymentType;
import pl.piasta.camperoo.payment.query.PaymentTypeProjection;
import pl.piasta.camperoo.payment.query.PaymentTypeQueryClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class PaymentTypeDatabaseRepository implements OrderPaymentTypeRepository, PaymentTypeQueryClient {
    private final PaymentTypeJpaRepository repository;

    @Override
    public PaymentType save(PaymentType paymentType) {
        return repository.save(paymentType);
    }

    @Override
    public PaymentType getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<PaymentType> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PaymentType> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<PaymentTypeProjection> findAllPaymentTypes() {
        return repository.findAllBy();
    }
}

