package pl.piasta.camperoo.infrastructure.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.product.domain.Product;
import pl.piasta.camperoo.product.query.ProductBasicProjection;
import pl.piasta.camperoo.product.query.ProductProjection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    Page<IdProjection> findAllIdsByTransportationIsNull(Pageable pageable);

    @EntityGraph("products-graph")
    List<ProductBasicProjection> findAllByTransportationIsNullAndIdIn(Collection<Long> ids, Sort sort);

    @EntityGraph("products-graph")
    Optional<ProductProjection> findOneById(Long id);
}
