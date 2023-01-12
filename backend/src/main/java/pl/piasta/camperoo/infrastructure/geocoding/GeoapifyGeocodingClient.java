package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import pl.piasta.camperoo.address.dto.RouteDistanceDto;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;
import pl.piasta.camperoo.common.dto.AddressDto;
import pl.piasta.camperoo.order.domain.OrderGeocodingClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class GeoapifyGeocodingClient implements AddressGeocodingQueryClient, OrderGeocodingClient {
    protected final GeocodingAutocompleteService autocompleteService;
    protected final GeocodingRoutingService routingService;

    @Override
    public Optional<RouteDistanceDto> findDistanceBetweenCoordinates(
            BigDecimal latitudeOrig, BigDecimal longitudeOrig,
            BigDecimal latitudeDest, BigDecimal longitudeDest
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
        return ListUtils.emptyIfNull(response.results());
    }

    private String toWaypoint(BigDecimal latitude, BigDecimal longitude) {
        return latitude + "," + longitude;
    }
}
