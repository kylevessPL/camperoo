package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.common.dto.ResourceCreatedDto;
import pl.piasta.camperoo.order.dto.CalculateOrderDto;
import pl.piasta.camperoo.order.dto.OrderCalculationDto;
import pl.piasta.camperoo.order.dto.PlaceOrderDto;
import pl.piasta.camperoo.order.dto.UpdateOrderStatusDto;

@RequiredArgsConstructor
@Transactional
public class OrderFacade {
    private final OrderConverter orderConverter;
    private final OrderEmailNotifier orderEmailNotifier;
    private final OrderManager orderManager;
    private final OrderPaymentManager paymentManager;

    @PreAuthorize("hasRole('ADMIN')")
    public void updateOrderStatus(Long orderId, UpdateOrderStatusDto dto) {
        var order = orderManager.changeOrderStatus(orderId, dto.getStatusId());
        orderEmailNotifier.sendOrderStatusChangeToken(order);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public OrderCalculationDto calculateOrderDetails(CalculateOrderDto dto) {
        var orderCalculationDetails = orderConverter.convertToOrderCalculationDetails(dto);
        var orderDetails = orderManager.calculateOrder(orderCalculationDetails);
        return orderConverter.convertToOrderCalculationDto(orderDetails);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER') and principal.id == #userId")
    public ResourceCreatedDto createOrder(Long userId, PlaceOrderDto dto) {
        var orderPlacementDetails = orderConverter.convertToOrderPlacementDetails(userId, dto);
        var orderCalculationDetails = orderConverter.convertToOrderCalculationDetails(dto);
        var orderCalculationResult = orderManager.calculateOrder(orderCalculationDetails);
        var order = orderManager.createOrder(orderPlacementDetails, orderCalculationResult);
        paymentManager.createPayment(order, dto.getPaymentTypeId());
        orderEmailNotifier.sendOrderStatusChangeToken(order);
        return new ResourceCreatedDto(order.getId());
    }
}
