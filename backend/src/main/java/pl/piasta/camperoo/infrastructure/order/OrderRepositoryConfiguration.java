package pl.piasta.camperoo.infrastructure.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.order.domain.OrderRepository;

@Configuration
class OrderRepositoryConfiguration {
    @Bean
    OrderRepository orderRepository(OrderJpaRepository jpaRepository) {
        return new OrderDatabaseRepository(jpaRepository);
    }
}
