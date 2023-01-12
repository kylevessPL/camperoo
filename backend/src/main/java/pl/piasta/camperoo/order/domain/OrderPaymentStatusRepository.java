package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.common.domain.Repository;
import pl.piasta.camperoo.payment.domain.PaymentStatus;

public interface OrderPaymentStatusRepository extends Repository<PaymentStatus, Long> {
}
