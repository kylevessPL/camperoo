package pl.piasta.camperoo.infrastructure.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class RestTemplateUtils {
    public URI buildUri(String uriString, Map<String, Object> params) {
        var map = params.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue().toString())));
        return buildUri(uriString, new LinkedMultiValueMap<>(map));
    }

    public URI buildUri(String uriString, MultiValueMap<String, String> params) {
        return UriComponentsBuilder.fromUriString(uriString)
                .queryParams(params)
                .build()
                .toUri();
    }
}
