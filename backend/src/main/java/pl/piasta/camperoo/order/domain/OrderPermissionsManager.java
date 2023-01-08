package pl.piasta.camperoo.order.domain;

public interface OrderPermissionsManager {
    boolean canAccess(Long userId, Long orderId);
}
