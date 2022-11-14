package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;
import pl.piasta.camperoo.common.util.CollectionUtils;
import pl.piasta.camperoo.infrastructure.util.RestTemplateUtils;
import pl.piasta.camperoo.order.domain.OrderGeocodingClient;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
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
        var uri = RestTemplateUtils.buildUri(GeocodingEndpoints.ROUTING, params);
        var response = getForItemType(client, uri, responseType);
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
        var uri = RestTemplateUtils.buildUri(GeocodingEndpoints.AUTOCOMPLETE, params);
        var response = getForItemType(client, uri, responseType);
        return CollectionUtils.emptyIfNull(response.results());
    }

    private String toWaypoint(double latitude, double longitude) {
        return latitude + "," + longitude;
    }

    private <T> GeocodingResponse<T> getForItemType(RestTemplate restTemplate, URI uri, Class<T> itemType) {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(GeocodingResponse.class, itemType);
        ParameterizedTypeReference<GeocodingResponse<T>> responseType =
                ParameterizedTypeReference.forType(resolvableType.getType());
        var response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
        return requireNonNull(response.getBody());
    }
}
