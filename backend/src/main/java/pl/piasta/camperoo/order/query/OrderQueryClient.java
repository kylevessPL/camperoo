package pl.piasta.camperoo.order.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrderQueryClient {
    @PreAuthorize("hasRole('ADMIN')")
    Page<OrderBasicProjection> findAllOrders(Pageable pageable);

    @PreAuthorize("isAuthenticated()")
    Page<OrderBasicProjection> findOrdersByUserId(Long userId, Pageable pageable);

    @PreAuthorize(
            "hasRole('ADMIN') or hasRole('CUSTOMER') and @orderPermissionsManager.canAccess(principal.id, #orderId)")
    OrderProjection findOrderById(Long orderId);
}
