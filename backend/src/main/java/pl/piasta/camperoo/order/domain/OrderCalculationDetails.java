package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.common.domain.vo.Coordinates;

import java.util.Map;

record OrderCalculationDetails(
        Map<Long, Integer> products,
        Coordinates destination,
        Long deliveryTypeId,
        String discountCode) {
}
