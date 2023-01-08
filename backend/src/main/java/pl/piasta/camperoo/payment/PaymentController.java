package pl.piasta.camperoo.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.payment.query.PaymentProjection;
import pl.piasta.camperoo.payment.query.PaymentQueryClient;
import pl.piasta.camperoo.payment.query.PaymentTypeProjection;
import pl.piasta.camperoo.payment.query.PaymentTypeQueryClient;
import pl.piasta.camperoo.security.TokenPrincipal;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
class PaymentController {
    private final PaymentQueryClient paymentQueryClient;
    private final PaymentTypeQueryClient paymentTypeQueryClient;

    @GetMapping
    Page<PaymentProjection> getAllPayments(Pageable pageable) {
        return paymentQueryClient.findAllPayments(pageable);
    }

    @GetMapping("/self")
    Page<PaymentProjection> getSelfPayments(@AuthenticationPrincipal TokenPrincipal principal, Pageable pageable) {
        return paymentQueryClient.findPaymentsByUserId(principal.id(), pageable);
    }

    @GetMapping("/types")
    List<PaymentTypeProjection> getAllPaymentTypes() {
        return paymentTypeQueryClient.findAllPaymentTypes();
    }
}
