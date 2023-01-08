package pl.piasta.camperoo.payment.domain;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;
import pl.piasta.camperoo.common.domain.LocalizableName;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_statuses")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class PaymentStatus extends AbstractEntity
        implements LocalizableName<PaymentStatusName>, LocalizableDescription<PaymentStatusDescription> {
    public static final Long INITIATED = 1L;
    public static final Long SUCCEEDED = 2L;
    public static final Long FAILED = 3L;
    public static final Long EXPIRED = 4L;
    public static final Long CANCELED = 5L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @OneToMany(mappedBy = "paymentStatus", fetch = FetchType.EAGER)
    private Set<PaymentStatusName> names;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @OneToMany(mappedBy = "paymentStatus", fetch = FetchType.EAGER)
    private Set<PaymentStatusDescription> descriptions;

    @OrderBy("deadline DESC")
    @OneToMany(mappedBy = "status")
    private List<Payment> payments;
}
