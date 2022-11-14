package pl.piasta.camperoo.infrastructure.recaptcha;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.lang.NonNull;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

record RecaptchaRequestInterceptor(String secret) implements ClientHttpRequestInterceptor {
    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
                                        @NonNull ClientHttpRequestExecution execution
    ) throws IOException {
        var uri = UriComponentsBuilder.fromHttpRequest(request)
                .queryParam(RecaptchaParams.SECRET, secret)
                .build(true)
                .toUri();
        var modifiedRequest = new RecaptchaHttpRequest(request, uri);
        return execution.execute(modifiedRequest, body);
    }

    private static class RecaptchaHttpRequest extends HttpRequestWrapper {
        private final URI uri;

        private RecaptchaHttpRequest(HttpRequest request, URI uri) {
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
