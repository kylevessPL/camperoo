package pl.piasta.camperoo.product.domain;

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
import pl.piasta.camperoo.common.domain.AbstractEntity;
import pl.piasta.camperoo.common.domain.LocalizableDescription;
import pl.piasta.camperoo.common.domain.LocalizableName;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Entity
@Table(name = "product_categories")
public class ProductCategory extends AbstractEntity
        implements LocalizableName<ProductCategoryName>, LocalizableDescription<ProductCategoryDescription> {
    @Id
    @SequenceGenerator(name = "gen_product_categories_id",
                       sequenceName = "seq_product_categories_id",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_product_categories_id")
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER)
    private Set<ProductCategoryName> names;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER)
    private Set<ProductCategoryDescription> descriptions;
}
