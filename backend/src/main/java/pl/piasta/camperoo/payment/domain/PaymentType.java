package pl.piasta.camperoo.payment.domain;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableName;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_types")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@NamedEntityGraph(
        name = "payment-types-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "name-description-child-graph",
                        attributeNodes = {
                                @NamedAttributeNode("locale")
                        }
                )
        }
)
public class PaymentType extends AbstractEntity implements LocalizableName<PaymentTypeName> {
    public static final Long BANK_TRANSFER = 1L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(nullable = false)
    private boolean active;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @OneToMany(mappedBy = "paymentType", fetch = FetchType.EAGER)
    private Set<PaymentTypeName> names;
}
