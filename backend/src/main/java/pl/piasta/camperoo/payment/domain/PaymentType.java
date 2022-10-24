package pl.piasta.camperoo.payment.domain;

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
@Table(name = "payment_types")
public class PaymentType extends AbstractEntity {
    public static final Long BANK_TRANSFER = 1L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "paymentType", fetch = FetchType.EAGER)
    private Set<PaymentTypeName> names;
}
