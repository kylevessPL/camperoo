package pl.piasta.camperoo.payment.domain;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.piasta.camperoo.common.domain.DescriptionOrientedEntity;
import pl.piasta.camperoo.global.domain.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_status_descriptions")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class PaymentStatusDescription extends DescriptionOrientedEntity<Locale> {
    @Id
    private Long id;

    @Column(nullable = false)
    private String description;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale_id", nullable = false)
    private Locale locale;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_status_id", nullable = false)
    private PaymentStatus paymentStatus;
}
