package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class GeocodingEndpoints {
    public static final String AUTOCOMPLETE = "/geocode/autocomplete?filter=countrycode:pl";
    public static final String ROUTING = "/routing?details=route_details&mode=light_truck&avoid=tolls|ferries&limit=1";
}
