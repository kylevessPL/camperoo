package pl.piasta.camperoo.order.domain;

import java.util.Optional;

public interface OrderGeocodingClient {
    <T> Optional<T> findDistanceBetweenCoordinates(
            double latitudeOrig, double longitudeOrig,
            double latitudeDest, double longitudeDest,
            Class<T> responseType);
}
