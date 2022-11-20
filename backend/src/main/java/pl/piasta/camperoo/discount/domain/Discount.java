package pl.piasta.camperoo.discount.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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

    @Builder.Default
    @OneToMany(mappedBy = "discount", fetch = FetchType.EAGER)
    private Set<DiscountDescription> descriptions = new HashSet<>();
}
