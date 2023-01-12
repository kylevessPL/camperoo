package pl.piasta.camperoo.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.common.util.PageBuilder;
import pl.piasta.camperoo.order.domain.OrderPaymentRepository;
import pl.piasta.camperoo.payment.domain.Payment;
import pl.piasta.camperoo.payment.query.PaymentProjection;
import pl.piasta.camperoo.payment.query.PaymentQueryClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class PaymentDatabaseRepository implements OrderPaymentRepository, PaymentQueryClient {
    private final PaymentJpaRepository repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment getReference(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Optional<Payment> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Payment> getAll() {
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
    public Page<PaymentProjection> findAllPayments(Pageable pageable) {
        var page = repository.findAllIdsBy(pageable);
        return findPaymentsByIdProjections(page);
    }

    @Override
    public Page<PaymentProjection> findPaymentsByUserId(Long userId, Pageable pageable) {
        var page = repository.findAllIdsByOrderUserId(userId, pageable);
        return findPaymentsByIdProjections(page);
    }

    private Page<PaymentProjection> findPaymentsByIdProjections(Page<IdProjection> idPage) {
        var ids = IdProjection.retrieveAllIds(idPage.toList());
        var content = repository.findAllByIdIn(ids, idPage.getSort());
        return PageBuilder.fromContent(content)
                .page(idPage)
                .build();
    }
}

