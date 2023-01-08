package pl.piasta.camperoo.infrastructure.order;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.order.domain.OrderPermissionsManager;

@RequiredArgsConstructor
class OrderAccessService implements OrderPermissionsManager {
    private final OrderJpaRepository repository;

    @Override
    public boolean canAccess(Long userId, Long orderId) {
        return repository.existsByIdAndUserId(orderId, userId);
    }
}
