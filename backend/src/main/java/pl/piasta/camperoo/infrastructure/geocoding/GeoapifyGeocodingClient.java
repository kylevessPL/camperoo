package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;
import pl.piasta.camperoo.common.util.CollectionUtils;
import pl.piasta.camperoo.order.domain.OrderGeocodingClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
class GeoapifyGeocodingClient implements AddressGeocodingQueryClient, OrderGeocodingClient {

    protected final RestTemplate client;

    @Override
    public <T> Optional<T> findDistanceBetweenCoordinates(
            double latitudeOrig, double longitudeOrig,
            double latitudeDest, double longitudeDest,
            Class<T> responseType
    ) {
        var waypoints = toWaypoint(latitudeOrig, longitudeOrig) + "|" + toWaypoint(latitudeDest, longitudeDest);
        Map<String, Object> params = Map.of(
                GeocodingParams.WAYPOINTS, waypoints,
                GeocodingParams.DETAILS, "route_details",
                GeocodingParams.MODE, "light_truck",
                GeocodingParams.AVOID, "tolls|ferries",
                GeocodingParams.LIMIT, 1
        );
        var uri = GeocodingUtils.buildUri(GeocodingEndpoints.ROUTING, params);
        var response = GeocodingUtils.getForItemType(client, uri, responseType);
        return CollectionUtils.emptyIfNull(response.results())
                .stream()
                .findFirst();
    }

    @Override
    public <T> List<T> findAllAddressesByQuery(String query, Class<T> responseType) {
        Map<String, Object> params = Map.of(
                GeocodingParams.QUERY, query,
                GeocodingParams.FILTER, "countrycode:pl"
        );
        var uri = GeocodingUtils.buildUri(GeocodingEndpoints.AUTOCOMPLETE, params);
        var response = GeocodingUtils.getForItemType(client, uri, responseType);
        return CollectionUtils.emptyIfNull(response.results());
    }

    private String toWaypoint(double latitude, double longitude) {
        return latitude + "," + longitude;
    }
}
