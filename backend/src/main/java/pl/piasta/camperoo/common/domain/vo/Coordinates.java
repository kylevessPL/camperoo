package pl.piasta.camperoo.common.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.StandardException;
import pl.piasta.camperoo.common.exception.ValidationException;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class Coordinates implements ValueObject {

    public static final double LATITUDE_BOUND = 90;
    public static final double LONGITUDE_BOUND = 180;

    private Double latitude;
    private Double longitude;

    private Coordinates(Double latitude, Double longitude) {
        this.latitude = validateLatitude(latitude);
        this.longitude = validateLongitude(longitude);
    }

    public static Coordinates of(Double latitude, Double longitude) {
        return new Coordinates(latitude, longitude);
    }

    private Double validateLatitude(Double latitude) {
        if (Math.ceil(Math.abs(latitude)) > LATITUDE_BOUND) {
            throw CoordinatesValidationException.latitudeOutOfBounds();
        }
        return latitude;
    }

    private Double validateLongitude(Double longitude) {
        if (Math.ceil(Math.abs(this.longitude)) > LONGITUDE_BOUND) {
            throw CoordinatesValidationException.longitudeOutOfBounds();
        }
        return longitude;
    }

    @StandardException(access = AccessLevel.PRIVATE)
    private static class CoordinatesValidationException extends ValidationException {

        public static CoordinatesValidationException latitudeOutOfBounds() {
            return new CoordinatesValidationException("Latitude is out of bounds");
        }

        public static CoordinatesValidationException longitudeOutOfBounds() {
            return new CoordinatesValidationException("Longitude is out of bounds");
        }
    }
}