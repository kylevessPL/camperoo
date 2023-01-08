package pl.piasta.camperoo.payment.query;

import pl.piasta.camperoo.common.query.NameProjection;

public interface PaymentTypeProjection extends NameProjection {
    Long getId();

    String getCode();

    boolean isActive();
}
