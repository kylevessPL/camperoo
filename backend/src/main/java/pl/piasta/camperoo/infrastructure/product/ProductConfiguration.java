package pl.piasta.camperoo.infrastructure.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class ProductConfiguration {
    @Bean
    ProductDatabaseRepository productRepository(
            ProductJpaRepository jpaRepository) {
        return new ProductDatabaseRepository(jpaRepository);
    }

    @Bean
    ProductCategoryDatabaseRepository productCategoryRepository(
            ProductCategoryJpaRepository jpaRepository) {
        return new ProductCategoryDatabaseRepository(jpaRepository);
    }
}
