package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
class GeoapifyClientAdapterFactory {
    public static final String GEOAPIFY_URL = "https://api.geoapify.com/v1";

    static WebClientAdapter create(String apiKey) {
        var client = WebClient.builder()
                .baseUrl(GEOAPIFY_URL)
                .filter(createRequestFilter(apiKey))
                .build();
        return WebClientAdapter.forClient(client);
    }

    private static ExchangeFilterFunction createRequestFilter(String apiKey) {
        return (request, next) -> {
            var clientRequest = ClientRequest.from(request)
                    .url(createBaseUrl(request.url(), apiKey))
                    .build();
            return next.exchange(clientRequest);
        };
    }

    private static URI createBaseUrl(URI currentUrl, String apiKey) {
        return UriComponentsBuilder.fromUri(currentUrl)
                .queryParam(GeocodingParams.API_KEY, apiKey)
                .queryParam(GeocodingParams.LOCALE, LocaleContextHolder.getLocale())
                .queryParam(GeocodingParams.FORMAT, "json")
                .build(true)
                .toUri();
    }
}
