package pl.piasta.camperoo.product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.piasta.camperoo.common.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "product_categories")
public class ProductCategory extends AbstractEntity {
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
