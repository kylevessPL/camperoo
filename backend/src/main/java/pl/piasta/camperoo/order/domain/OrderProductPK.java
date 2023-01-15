package pl.piasta.camperoo.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderProductPK implements Serializable {
    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long productId;
}
