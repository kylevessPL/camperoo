package pl.piasta.camperoo.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class OrderProductPK implements Serializable {
    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long productId;
}
