package pl.piasta.camperoo.infrastructure.geocoding;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import pl.piasta.camperoo.common.dto.AddressDto;

import static pl.piasta.camperoo.infrastructure.geocoding.GeocodingParams.TEXT;

@HttpExchange(GeocodingEndpoints.AUTOCOMPLETE)
interface GeocodingAutocompleteService {
    @GetExchange
    GeocodingResponse<AddressDto> findAllAddressesByQuery(@RequestParam(TEXT) String query);
}
