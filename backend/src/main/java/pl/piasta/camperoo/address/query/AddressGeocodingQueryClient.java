package pl.piasta.camperoo.address.query;

import java.util.List;
import java.util.Optional;

public interface AddressGeocodingQueryClient {
    <T> Optional<T> findDistanceBetweenCoordinates(
            double latitudeOrig, double longitudeOrig,
            double latitudeDest, double longitudeDest,
            Class<T> responseType
    );

    <T> List<T> findAllAddressesByQuery(String query, Class<T> responseType);
}
