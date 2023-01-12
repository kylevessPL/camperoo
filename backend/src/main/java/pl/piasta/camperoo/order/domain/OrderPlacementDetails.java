package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.common.domain.vo.Coordinates;

record OrderPlacementDetails(Long userId, Long deliveryTypeId, Coordinates destination, String address, String notes) {
}
