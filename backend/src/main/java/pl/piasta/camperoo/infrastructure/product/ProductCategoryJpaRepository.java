package pl.piasta.camperoo.infrastructure.product;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.product.domain.ProductCategory;

import java.util.List;

@Repository
public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategory, Long> {
    @EntityGraph("product-categories-graph")
    List<ConstantProjection> findAllBy();
}
