package pl.piasta.camperoo.product.query;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.query.ConstantProjection;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductCategoryQueryClient {
    @PreAuthorize("permitAll()")
    List<ConstantProjection> findAllProductCategories();
}
