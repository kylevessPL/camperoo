package pl.piasta.camperoo.order.domain;

import pl.piasta.camperoo.address.dto.RouteDistanceDto;

import java.util.Optional;

public interface OrderGeocodingClient {
    Optional<RouteDistanceDto> findDistanceBetweenCoordinates(
            double latitudeOrig, double longitudeOrig,
            double latitudeDest, double longitudeDest);
}
