package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class GeocodingParams {
    public static final String API_KEY = "apiKey";
    public static final String LOCALE = "lang";
    public static final String FORMAT = "format";
    public static final String WAYPOINTS = "waypoints";
    public static final String TEXT = "text";
}
