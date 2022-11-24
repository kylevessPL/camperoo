package pl.piasta.camperoo.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.NameOrientedEntity;
import pl.piasta.camperoo.global.domain.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_type_names")
public class PaymentTypeName extends NameOrientedEntity<Locale> {
    @Id
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locale_id", nullable = false)
    private Locale locale;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType;
}
