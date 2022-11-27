package pl.piasta.camperoo.infrastructure.captcha;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@UtilityClass
class RecaptchaClientAdapterFactory {
    public final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api";

    WebClientAdapter create(String secret) {
        var client = WebClient.builder()
                .baseUrl(RECAPTCHA_URL)
                .filter(createRequestFilter(secret))
                .build();
        return WebClientAdapter.forClient(client);
    }

    private ExchangeFilterFunction createRequestFilter(String secret) {
        return (request, next) -> {
            var clientRequest = ClientRequest.from(request)
                    .url(createBaseUrl(request.url(), secret))
                    .build();
            return next.exchange(clientRequest);
        };
    }

    private URI createBaseUrl(URI currentUrl, String secret) {
        return UriComponentsBuilder.fromUri(currentUrl)
                .queryParam(CaptchaParams.SECRET, secret)
                .build(true)
                .toUri();
    }
}
