package pl.piasta.camperoo.payment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableName;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_types")
public class PaymentType extends AbstractEntity implements LocalizableName<PaymentTypeName> {
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
