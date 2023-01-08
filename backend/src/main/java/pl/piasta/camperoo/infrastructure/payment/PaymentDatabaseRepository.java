package pl.piasta.camperoo.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.common.util.PageBuilder;
import pl.piasta.camperoo.payment.query.PaymentProjection;
import pl.piasta.camperoo.payment.query.PaymentQueryClient;

@RequiredArgsConstructor
class PaymentDatabaseRepository implements PaymentQueryClient {
    private final PaymentJpaRepository repository;

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

