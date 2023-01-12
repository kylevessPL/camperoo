package pl.piasta.camperoo.infrastructure.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.order.domain.OrderPermissionsManager;

@Configuration
class OrderConfiguration {
    @Bean
    OrderDatabaseRepository orderRepository(OrderJpaRepository jpaRepository) {
        return new OrderDatabaseRepository(jpaRepository);
    }

    @Bean
    OrderStatusDatabaseRepository orderStatusRepository(OrderStatusJpaRepository jpaRepository) {
        return new OrderStatusDatabaseRepository(jpaRepository);
    }

    @Bean
    OrderPermissionsManager orderPermissionsManager(OrderJpaRepository jpaRepository) {
        return new OrderAccessService(jpaRepository);
    }
}
