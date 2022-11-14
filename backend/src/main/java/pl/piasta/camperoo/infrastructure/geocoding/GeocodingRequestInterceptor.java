package pl.piasta.camperoo.infrastructure.geocoding;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.lang.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

record GeocodingRequestInterceptor(String apiKey) implements ClientHttpRequestInterceptor {
    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
                                        @NonNull ClientHttpRequestExecution execution
    ) throws IOException {
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
