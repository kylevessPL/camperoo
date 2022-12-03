package pl.piasta.camperoo.discount.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "discounts")
public class Discount extends AbstractEntity implements LocalizableDescription<DiscountDescription> {
    @Id
    @SequenceGenerator(name = "gen_discounts_id", sequenceName = "seq_discounts_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_discounts_id")
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(nullable = false)
    private Integer value;

    private Instant expirationDate;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @Singular
    @OneToMany(mappedBy = "discount", fetch = FetchType.EAGER)
    private Set<DiscountDescription> descriptions = Collections.emptySet();
}
