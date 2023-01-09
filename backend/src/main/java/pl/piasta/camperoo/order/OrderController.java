package pl.piasta.camperoo.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.piasta.camperoo.order.domain.OrderFacade;
import pl.piasta.camperoo.order.dto.UpdateOrderStatusDto;
import pl.piasta.camperoo.order.query.OrderBasicProjection;
import pl.piasta.camperoo.order.query.OrderProjection;
import pl.piasta.camperoo.order.query.OrderQueryClient;
import pl.piasta.camperoo.security.TokenPrincipal;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrderController {
    private final OrderFacade orderFacade;
    private final OrderQueryClient orderQueryClient;

    @GetMapping
    Page<OrderBasicProjection> getAllOrders(Pageable pageable) {
        return orderQueryClient.findAllOrders(pageable);
    }

    @GetMapping("/self")
    Page<OrderBasicProjection> getSelfOrders(@AuthenticationPrincipal TokenPrincipal principal, Pageable pageable) {
        return orderQueryClient.findOrdersByUserId(principal.id(), pageable);
    }

    @GetMapping("/{id}")
    OrderProjection getOrder(@PathVariable Long id) {
        return orderQueryClient.findOrderById(id);
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/{id}/status")
    void updateOrderStatus(@PathVariable Long id, @RequestBody @Valid UpdateOrderStatusDto dto) {
        orderFacade.updateOrderStatus(id, dto);
    }
}
