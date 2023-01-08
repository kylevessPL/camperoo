package pl.piasta.camperoo.payment.query;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PaymentTypeQueryClient {
    @PreAuthorize("permitAll()")
    List<PaymentTypeProjection> findAllPaymentTypes();
}
