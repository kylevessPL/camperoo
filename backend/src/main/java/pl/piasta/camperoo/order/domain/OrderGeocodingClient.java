package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.address.dto.RouteDistanceDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderGeocodingClient {
    Optional<RouteDistanceDto> findDistanceBetweenCoordinates(
            BigDecimal latitudeOrig, BigDecimal longitudeOrig,
            BigDecimal latitudeDest, BigDecimal longitudeDest);
}
