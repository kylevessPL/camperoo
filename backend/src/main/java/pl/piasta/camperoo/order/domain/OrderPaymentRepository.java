package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.payment.domain.Payment;

public interface OrderPaymentRepository extends Repository<Payment, Long> {
}
