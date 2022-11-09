package pl.piasta.camperoo.infrastructure.geocoding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;

@Configuration
class GeocodingClientConfiguration {
    @Bean
    AddressGeocodingQueryClient geocodingQueryClient(@Value("${GEOAPIFY_API_KEY}") String apiKey) {
        var restTemplate = RestTemplateFactory.create(apiKey);
        return new GeoapifyGeocodingClient(restTemplate);
    }
}
