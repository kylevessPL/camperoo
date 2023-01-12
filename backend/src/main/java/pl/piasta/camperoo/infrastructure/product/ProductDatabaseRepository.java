package pl.piasta.camperoo.infrastructure.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.piasta.camperoo.common.query.IdProjection;
import pl.piasta.camperoo.common.util.PageBuilder;
import pl.piasta.camperoo.order.domain.OrderProductRepository;
import pl.piasta.camperoo.product.domain.Product;
import pl.piasta.camperoo.product.exception.ProductNotFoundException;
import pl.piasta.camperoo.product.query.ProductBasicProjection;
import pl.piasta.camperoo.product.query.ProductProjection;
import pl.piasta.camperoo.product.query.ProductQueryClient;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
class ProductDatabaseRepository implements OrderProductRepository, ProductQueryClient {
    private final ProductJpaRepository repository;

    @Override
    public Page<ProductBasicProjection> findAllProducts(Pageable pageable) {
        var page = repository.findAllIdsByTransportationIsNull(pageable);
        var ids = IdProjection.retrieveAllIds(page.toList());
        var content = repository.findAllByTransportationIsNullAndIdIn(ids, pageable.getSort());
        return PageBuilder.fromContent(content)
                .page(page)
                .build();
    }

    @Override
    public ProductProjection findProductById(Long productId) {
        return repository
                .findOneById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public List<Product> getAllByIdIn(Set<Long> ids) {
        return repository.findAllByTransportationIsNullAndIdIn(ids);
    }

    @Override
    public Product getTransportationProduct() {
        return repository.findByTransportationIsTrue();
    }
}

