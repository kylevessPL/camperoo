package pl.piasta.camperoo.infrastructure.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.order.domain.OrderPermissionsManager;

@Configuration
@ComponentScan
class OrderConfiguration {
    @Bean
    OrderDatabaseRepository orderRepository(OrderJpaRepository jpaRepository) {
        return new OrderDatabaseRepository(jpaRepository);
    }

    @Bean
    OrderPermissionsManager orderPermissionsManager(OrderJpaRepository jpaRepository) {
        return new OrderAccessService(jpaRepository);
    }
}
