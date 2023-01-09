package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.camperoo.order.dto.UpdateOrderStatusDto;

@RequiredArgsConstructor
@Transactional
public class OrderFacade {
    private final OrderManager orderManager;
    private final OrderInvoiceManager invoiceManager;

    @PreAuthorize("hasRole('ADMIN')")
    public void updateOrderStatus(Long userId, UpdateOrderStatusDto dto) {
        orderManager.updateOrderStatus(userId, dto.getStatusCode());
    }
}
