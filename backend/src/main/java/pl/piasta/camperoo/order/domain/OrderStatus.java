package pl.piasta.camperoo.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_statuses")
public class OrderStatus extends AbstractEntity {
    public static final Long PLACED = 1L;
    public static final Long PROCESSED = 2L;
    public static final Long IN_TRANSIT = 3L;
    public static final Long PICKUP_READY = 4L;
    public static final Long COMPLETED = 5L;
    public static final Long CANCELED = 6L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.EAGER)
    private Set<OrderStatusName> names;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.EAGER)
    private Set<OrderStatusDescription> descriptions;
}
