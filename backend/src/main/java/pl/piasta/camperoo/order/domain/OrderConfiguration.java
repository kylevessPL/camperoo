package pl.piasta.camperoo.order.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {
    @Bean
    OrderFacade orderFacade(OrderRepository orderRepository, OrderStatusRepository orderStatusRepository) {
        var orderManager = new OrderManager(orderRepository, orderStatusRepository);
        var invoiceManager = new OrderInvoiceManager(orderRepository);
        return new OrderFacade(orderManager, invoiceManager);
    }
}
