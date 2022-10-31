package pl.piasta.camperoo.infrastructure.geocoding;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class GeocodingParams {
    public static final String API_KEY = "apiKey";
    public static final String LOCALE = "lang";
    public static final String FORMAT = "format";
    public static final String LIMIT = "limit";
    public static final String FILTER = "filter";
    public static final String DETAILS = "details";
    public static final String MODE = "mode";
    public static final String AVOID = "avoid";
    public static final String WAYPOINTS = "waypoints";
    public static final String QUERY = "text";
}
