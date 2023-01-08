package pl.piasta.camperoo.payment.query;

import org.springframework.beans.factory.annotation.Value;
import pl.piasta.camperoo.common.query.ConstantProjection;

import java.time.Instant;

public interface PaymentProjection {
    Long getId();

    @Value("#{target.isValid()}")
    boolean isValid();

    Instant getStatusChangeDate();

    PaymentTypeProjection getType();

    ConstantProjection getStatus();
}
