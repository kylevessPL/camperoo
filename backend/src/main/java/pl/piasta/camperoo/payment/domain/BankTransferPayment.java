package pl.piasta.camperoo.payment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.order.domain.Order;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("1")
@Entity
public class BankTransferPayment extends Payment {
    @Column(length = 34)
    private String iban;

    @Column(length = 11)
    private String swiftCode;

    @Builder
    private BankTransferPayment(Long id, Instant deadline, Instant statusChangeDate, PaymentStatus status, Order order,
                                String iban, String swiftCode) {
        super(id, deadline, statusChangeDate, status, order);
        this.iban = iban;
        this.swiftCode = swiftCode;
    }
}
