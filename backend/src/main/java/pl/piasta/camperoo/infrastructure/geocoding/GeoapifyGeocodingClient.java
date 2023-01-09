package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.RequiredArgsConstructor;
import pl.piasta.camperoo.address.dto.RouteDistanceDto;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;
import pl.piasta.camperoo.branch.domain.CompanyBranchGeocodingClient;
import pl.piasta.camperoo.common.dto.AddressDto;
import pl.piasta.camperoo.common.util.CollectionUtils;
import pl.piasta.camperoo.order.domain.OrderGeocodingClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class GeoapifyGeocodingClient
        implements AddressGeocodingQueryClient, CompanyBranchGeocodingClient, OrderGeocodingClient {
    protected final GeocodingAutocompleteService autocompleteService;
    protected final GeocodingRoutingService routingService;

    @Override
    public Optional<RouteDistanceDto> findDistanceBetweenCoordinates(
            double latitudeOrig, double longitudeOrig,
            double latitudeDest, double longitudeDest
    ) {
        var waypoints = toWaypoint(latitudeOrig, longitudeOrig) + "|" + toWaypoint(latitudeDest, longitudeDest);
        var response = routingService.findDistanceBetweenCoordinates(waypoints);
        return CollectionUtils.emptyIfNull(response.results())
                .stream()
                .findFirst();
    }

    @Override
    public List<AddressDto> findAllAddressesByQuery(String query) {
        var response = autocompleteService.findAllAddressesByQuery(query);
        return CollectionUtils.emptyIfNull(response.results());
    }

    private String toWaypoint(double latitude, double longitude) {
        return latitude + "," + longitude;
    }
}
