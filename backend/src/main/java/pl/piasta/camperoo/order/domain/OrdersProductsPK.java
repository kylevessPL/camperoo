package pl.piasta.camperoo.order.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class OrdersProductsPK implements Serializable {
    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long productId;
}
