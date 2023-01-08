package pl.piasta.camperoo.product.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductQueryClient {
    @PreAuthorize("permitAll()")
    Page<ProductBasicProjection> findAllProducts(Pageable pageable);

    @PreAuthorize("permitAll()")
    ProductProjection findProductById(Long id);
}
