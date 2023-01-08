package pl.piasta.camperoo.infrastructure.geocoding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import pl.piasta.camperoo.address.query.AddressGeocodingQueryClient;

@Configuration
class GeocodingConfiguration {
    @Bean
    AddressGeocodingQueryClient geocodingQueryClient(@Value("${geoapify.apiKey}") String apiKey) {
        var serviceFactory = HttpServiceProxyFactory.builder(GeoapifyClientAdapterFactory.create(apiKey)).build();
        var autocompleteService = serviceFactory.createClient(GeocodingAutocompleteService.class);
        var routingService = serviceFactory.createClient(GeocodingRoutingService.class);
        return new GeoapifyGeocodingClient(autocompleteService, routingService);
    }
}
