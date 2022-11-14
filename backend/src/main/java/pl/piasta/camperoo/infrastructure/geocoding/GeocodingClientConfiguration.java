package pl.piasta.camperoo.infrastructure.geocoding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;
import pl.piasta.camperoo.infrastructure.util.RestTemplateFactory;

@Configuration
class GeocodingClientConfiguration {
    @Bean
    AddressGeocodingQueryClient geocodingQueryClient(@Value("${GEOAPIFY_API_KEY}") String apiKey) {
        var requestInterceptor = new GeocodingRequestInterceptor(apiKey);
        var restTemplate = RestTemplateFactory.create(requestInterceptor);
        return new GeoapifyGeocodingClient(restTemplate);
    }
}
