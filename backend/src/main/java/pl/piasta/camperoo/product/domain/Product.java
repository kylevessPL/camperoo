package pl.piasta.camperoo.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;
import pl.piasta.camperoo.common.domain.LocalizableName;
import pl.piasta.camperoo.file.domain.File;

import java.math.BigDecimal;
import java.util.Set;

import static java.util.Objects.requireNonNullElse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "products")
@NamedEntityGraph(
        name = "products-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph"),
                @NamedAttributeNode(value = "descriptions", subgraph = "name-description-child-graph"),
                @NamedAttributeNode(value = "category", subgraph = "name-description-graph"),
                @NamedAttributeNode(value = "image", subgraph = "image-child-graph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "name-description-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "names", subgraph = "name-description-child-graph"),
                                @NamedAttributeNode(value = "descriptions", subgraph = "name-description-child-graph"),
                        }
                ),
                @NamedSubgraph(
                        name = "name-description-child-graph",
                        attributeNodes = {
                                @NamedAttributeNode("locale")
                        }
                ),
                @NamedSubgraph(
                        name = "image-child-graph",
                        attributeNodes = {
                                @NamedAttributeNode("content")
                        }
                )
        }
)
public class Product extends AbstractEntity
        implements LocalizableName<ProductName>, LocalizableDescription<ProductDescription> {
    @Id
    @SequenceGenerator(name = "gen_products_id", sequenceName = "seq_products_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_products_id")
    private Long id;

    @Column(nullable = false, precision = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ProductName> names;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ProductDescription> descriptions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private File image;

    @Builder.Default
    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(nullable = false, insertable = false, updatable = false)
    private Boolean transportation;

    @PostLoad
    void initFallback() {
        transportation = requireNonNullElse(transportation, false);
    }
}
