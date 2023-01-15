package pl.piasta.camperoo.delivery.domain;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import pl.piasta.camperoo.common.domain.LocalizableDescription;
import pl.piasta.camperoo.common.domain.LocalizableName;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "delivery_types")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@NamedEntityGraph(
        name = "delivery-types-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph"),
                @NamedAttributeNode(value = "descriptions", subgraph = "name-description-child-graph"),
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
public class DeliveryType extends AbstractEntity
        implements LocalizableName<DeliveryTypeName>, LocalizableDescription<DeliveryTypeDescription> {
    public static final Long DOOR_TO_DOOR = 1L;
    public static final Long SELF_PICKUP = 2L;

    @Id
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @OneToMany(mappedBy = "deliveryType")
    private Set<DeliveryTypeName> names;

    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @OneToMany(mappedBy = "deliveryType")
    private Set<DeliveryTypeDescription> descriptions;
}
