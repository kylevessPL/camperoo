package pl.piasta.camperoo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.common.query.ConstantProjection;
import pl.piasta.camperoo.product.query.ProductBasicProjection;
import pl.piasta.camperoo.product.query.ProductCategoryQueryClient;
import pl.piasta.camperoo.product.query.ProductProjection;
import pl.piasta.camperoo.product.query.ProductQueryClient;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
class ProductController {
    private final ProductQueryClient productQueryClient;
    private final ProductCategoryQueryClient productCategoryQueryClient;

    @GetMapping
    Page<ProductBasicProjection> getAllProducts(Pageable pageable) {
        return productQueryClient.findAllProducts(pageable);
    }

    @GetMapping("/{id}")
    ProductProjection getProduct(@PathVariable Long id) {
        return productQueryClient.findProductById(id);
    }

    @GetMapping("/categories")
    List<ConstantProjection> getAllProductCategories() {
        return productCategoryQueryClient.findAllProductCategories();
    }
}
