package pl.piasta.camperoo.order.domain;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;
import pl.piasta.camperoo.common.domain.LocalizableName;

import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_statuses")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class OrderStatus extends AbstractEntity
        implements LocalizableName<OrderStatusName>, LocalizableDescription<OrderStatusDescription> {
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

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.EAGER)
    private Set<OrderStatusName> names;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.EAGER)
    private Set<OrderStatusDescription> descriptions;

    public boolean isProcessed() {
        return Objects.equals(id, PROCESSED);
    }

    public boolean isCanceled() {
        return Objects.equals(id, CANCELED);
    }
}
