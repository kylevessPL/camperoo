package pl.piasta.camperoo.order.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {
    @Bean
    OrderFacade orderFacade(OrderRepository orderRepository) {
        var invoiceManager = new OrderInvoiceManager(orderRepository);
        return new OrderFacade(invoiceManager);
    }
}
