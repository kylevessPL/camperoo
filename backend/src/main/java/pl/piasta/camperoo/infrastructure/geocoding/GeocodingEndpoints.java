package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class GeocodingEndpoints {
    public static final String AUTOCOMPLETE = "https://api.geoapify.com/v1/geocode/autocomplete";
    public static final String ROUTING = "https://api.geoapify.com/v1/routing";
}
