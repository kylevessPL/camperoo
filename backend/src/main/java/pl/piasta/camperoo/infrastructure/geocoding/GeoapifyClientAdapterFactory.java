package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@UtilityClass
class GeoapifyClientAdapterFactory {
    public final String GEOAPIFY_URL = "https://api.geoapify.com/v1";

    WebClientAdapter create(String apiKey) {
        var client = WebClient.builder()
                .baseUrl(GEOAPIFY_URL)
                .filter(createRequestFilter(apiKey))
                .build();
        return WebClientAdapter.forClient(client);
    }

    private ExchangeFilterFunction createRequestFilter(String apiKey) {
        return (request, next) -> {
            var clientRequest = ClientRequest.from(request)
                    .url(createBaseUrl(request.url(), apiKey))
                    .build();
            return next.exchange(clientRequest);
        };
    }

    private URI createBaseUrl(URI currentUrl, String apiKey) {
        return UriComponentsBuilder.fromUri(currentUrl)
                .queryParam(GeocodingParams.API_KEY, apiKey)
                .queryParam(GeocodingParams.LOCALE, LocaleContextHolder.getLocale())
                .queryParam(GeocodingParams.FORMAT, "json")
                .build(true)
                .toUri();
    }
}
