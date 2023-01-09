package pl.piasta.camperoo.branch.domain;

import pl.piasta.camperoo.address.dto.RouteDistanceDto;

import java.util.Optional;

public interface CompanyBranchGeocodingClient {
    Optional<RouteDistanceDto> findDistanceBetweenCoordinates(
            double latitudeOrig, double longitudeOrig,
            double latitudeDest, double longitudeDest
    );
}
