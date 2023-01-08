package pl.piasta.camperoo.infrastructure.product;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.product.query.ProductCategoryQueryClient;

import java.util.List;

@RequiredArgsConstructor
class ProductCategoryDatabaseRepository implements ProductCategoryQueryClient {
    private final ProductCategoryJpaRepository repository;

    @Override
    public List<ConstantProjection> findAllProductCategories() {
        return repository.findAllBy();
    }
}

