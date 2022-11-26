package pl.piasta.camperoo.address.query;

import pl.piasta.camperoo.address.dto.RouteDistanceDto;
import pl.piasta.camperoo.common.dto.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressGeocodingQueryClient {
    Optional<RouteDistanceDto> findDistanceBetweenCoordinates(
            double latitudeOrig, double longitudeOrig,
            double latitudeDest, double longitudeDest
    );

    List<AddressDto> findAllAddressesByQuery(String query);
}
