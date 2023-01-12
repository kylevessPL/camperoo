package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.exception.PaymentTypeNotFoundException;
import pl.piasta.camperoo.payment.domain.BankTransferPayment;
import pl.piasta.camperoo.payment.domain.PaymentStatus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
class OrderPaymentManager {
    private final OrderPaymentDetailsProvider paymentDetailsProvider;
    private final OrderPaymentRepository paymentRepository;
    private final OrderPaymentTypeRepository paymentTypeRepository;
    private final OrderPaymentStatusRepository paymentStatusRepository;
    private final long paymentDeadlineDays;

    public void createPayment(Order order, Long paymentTypeId) {
        if (!paymentTypeRepository.exists(paymentTypeId)) {
            throw new PaymentTypeNotFoundException(paymentTypeId);
        }
        var paymentStatus = paymentStatusRepository.getReference(PaymentStatus.INITIATED);
        var paymentDetails = paymentDetailsProvider.generateOrderPaymentDetails();
        var creationDate = Instant.now();
        var payment = BankTransferPayment.builder()
                .iban(paymentDetails.iban())
                .swiftCode(paymentDetails.swiftCode())
                .status(paymentStatus)
                .statusChangeDate(creationDate)
                .deadline(creationDate.plus(paymentDeadlineDays, ChronoUnit.DAYS))
                .order(order)
                .build();
        paymentRepository.save(payment);
    }
}
