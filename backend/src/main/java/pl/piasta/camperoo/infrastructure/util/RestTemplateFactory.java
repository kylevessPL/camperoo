package pl.piasta.camperoo.infrastructure.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode;
import org.springframework.web.util.UriTemplateHandler;

import java.util.Arrays;

@UtilityClass
public class RestTemplateFactory {
    public RestTemplate create(ClientHttpRequestInterceptor... requestInterceptors) {
        return createRestTemplate(requestInterceptors);
    }

    private RestTemplate createRestTemplate(ClientHttpRequestInterceptor... requestInterceptors) {
        var restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(createUriTemplateHandler());
        restTemplate.setInterceptors(Arrays.asList(requestInterceptors));
        return restTemplate;
    }

    private UriTemplateHandler createUriTemplateHandler() {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(EncodingMode.NONE);
        return defaultUriBuilderFactory;
    }
}
