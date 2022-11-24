package pl.piasta.camperoo.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;
import pl.piasta.camperoo.common.domain.LocalizableName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_statuses")
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

    @OneToMany(mappedBy = "paymentStatus", fetch = FetchType.EAGER)
    private Set<PaymentStatusName> names;

    @OneToMany(mappedBy = "paymentStatus", fetch = FetchType.EAGER)
    private Set<PaymentStatusDescription> descriptions;

    @OrderBy("deadline DESC")
    @OneToMany(mappedBy = "status")
    private List<Payment> payments;
}
