package pl.piasta.camperoo.payment.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PaymentQueryClient {
    @PreAuthorize("hasRole('ADMIN')")
    Page<PaymentProjection> findAllPayments(Pageable pageable);

    @PreAuthorize("isAuthenticated()")
    Page<PaymentProjection> findPaymentsByUserId(Long userId, Pageable pageable);
}
