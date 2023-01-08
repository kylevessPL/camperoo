package pl.piasta.camperoo.order.query;

import org.springframework.beans.factory.annotation.Value;
import pl.piasta.camperoo.common.query.ConstantProjection;

import java.math.BigDecimal;
import java.time.Instant;

public interface OrderBasicProjection {
    Long getId();

    Instant getPlacementDate();

    Instant getStatusChangeDate();

    BigDecimal getTotalPrice();

    ConstantProjection getDeliveryType();

    ConstantProjection getStatus();

    @Value("#{target.invoice?.uuid}")
    Long getInvoiceUuid();
}
