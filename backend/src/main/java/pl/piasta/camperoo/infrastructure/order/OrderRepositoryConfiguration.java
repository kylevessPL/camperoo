package pl.piasta.camperoo.infrastructure.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
class OrderRepositoryConfiguration {
    @Bean
    OrderDatabaseRepository orderRepository(OrderJpaRepository jpaRepository) {
        return new OrderDatabaseRepository(jpaRepository);
    }
}
