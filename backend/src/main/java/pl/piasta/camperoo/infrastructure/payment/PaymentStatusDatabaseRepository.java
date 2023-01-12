package pl.piasta.camperoo.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.domain.OrderPaymentStatusRepository;
import pl.piasta.camperoo.payment.domain.PaymentStatus;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class PaymentStatusDatabaseRepository implements OrderPaymentStatusRepository {
    private final PaymentStatusJpaRepository repository;

    @Override
    public PaymentStatus save(PaymentStatus paymentStatus) {
        return repository.save(paymentStatus);
    }

    @Override
    public PaymentStatus getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<PaymentStatus> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PaymentStatus> getAll() {
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
}

