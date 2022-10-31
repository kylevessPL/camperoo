package pl.piasta.camperoo.infrastructure.geocoding;

import java.util.List;

record GeocodingResponse<T>(List<T> results) {
}
