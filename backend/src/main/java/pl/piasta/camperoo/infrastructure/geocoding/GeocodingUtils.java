package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class GeocodingUtils {

    static <T> GeocodingResponse<T> getForItemType(RestTemplate restTemplate, URI uri, Class<T> itemType) {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(GeocodingResponse.class, itemType);
        ParameterizedTypeReference<GeocodingResponse<T>> responseType =
                ParameterizedTypeReference.forType(resolvableType.getType());
        var response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
        return requireNonNull(response.getBody());
    }

    static URI buildUri(String uriString, Map<String, Object> params) {
        var map = params.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue().toString())));
        return buildUri(uriString, new LinkedMultiValueMap<>(map));
    }

    static URI buildUri(String uriString, MultiValueMap<String, String> params) {
        return UriComponentsBuilder.fromUriString(uriString)
                .queryParams(params)
                .build()
                .toUri();
    }
}
