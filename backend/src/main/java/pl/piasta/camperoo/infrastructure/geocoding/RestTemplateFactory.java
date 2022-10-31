package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplateHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class RestTemplateFactory {

    static RestTemplate create(final String apiKey) {
        return createRestTemplate(apiKey);
    }

    private static RestTemplate createRestTemplate(final String apiKey) {
        var restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(createUriTemplateHandler());
        restTemplate.setInterceptors(Collections.singletonList(new GeocodingQueryParamsInterceptor(apiKey)));
        return restTemplate;
    }

    private static UriTemplateHandler createUriTemplateHandler() {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        return defaultUriBuilderFactory;
    }

    private record GeocodingQueryParamsInterceptor(String apiKey) implements ClientHttpRequestInterceptor {

        @Override
        @NonNull
        public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
                                            @NonNull ClientHttpRequestExecution execution)
                throws IOException {
            var uri = UriComponentsBuilder.fromHttpRequest(request)
                    .queryParam(GeocodingParams.API_KEY, apiKey)
                    .queryParam(GeocodingParams.LOCALE, LocaleContextHolder.getLocale())
                    .queryParam(GeocodingParams.FORMAT, "json")
                    .build(true)
                    .toUri();
            var modifiedRequest = new GeoapifyHttpRequest(request, uri);
            return execution.execute(modifiedRequest, body);
        }

        private static class GeoapifyHttpRequest extends HttpRequestWrapper {
            private final URI uri;

            private GeoapifyHttpRequest(HttpRequest request, URI uri) {
                super(request);
                this.uri = uri;
            }

            @Override
            @NonNull
            public URI getURI() {
                return uri;
            }
        }
    }
}
