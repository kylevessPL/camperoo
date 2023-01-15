package pl.piasta.camperoo.common.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Range;
import pl.piasta.camperoo.common.exception.ErrorProperty;
import pl.piasta.camperoo.common.exception.ValidationException;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class Coordinates implements ValueObject {

    public static final String LATITUDE_MIN_VALUE = "-90";
    public static final String LATITUDE_MAX_VALUE = "90";
    public static final String LONGITUDE_MIN_VALUE = "-180";
    public static final String LONGITUDE_MAX_VALUE = "180";

    private BigDecimal latitude;
    private BigDecimal longitude;

    public Coordinates(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = validateLatitude(latitude);
        this.longitude = validateLongitude(longitude);
    }

    public static Coordinates of(BigDecimal latitude, BigDecimal longitude) {
        return new Coordinates(latitude, longitude);
    }

    private BigDecimal validateLatitude(BigDecimal latitude) {
        if (isOutOfBounds(LATITUDE_MIN_VALUE, LATITUDE_MAX_VALUE, latitude)) {
            throw CoordinatesValidationException.latitudeOutOfBounds();
        }
        return latitude;
    }

    private BigDecimal validateLongitude(BigDecimal longitude) {
        if (isOutOfBounds(LONGITUDE_MIN_VALUE, LONGITUDE_MAX_VALUE, longitude)) {
            throw CoordinatesValidationException.longitudeOutOfBounds();
        }
        return longitude;
    }

    private boolean isOutOfBounds(String coordinateMin, String coordinateMax, BigDecimal coordinate) {
        var start = new BigDecimal(coordinateMin);
        var end = new BigDecimal(coordinateMax);
        return !Range.between(start, end).contains(coordinate);
    }

    private static class CoordinatesValidationException extends ValidationException {
        private CoordinatesValidationException(String message, ErrorProperty property, String coordinateType) {
            super(message, property, coordinateType);
        }

        public static CoordinatesValidationException latitudeOutOfBounds() {
            return new CoordinatesValidationException(
                    "Latitude is out of bounds",
                    ErrorProperty.COORDINATE_OUT_OF_BOUNDS,
                    "latitude"
            );
        }

        public static CoordinatesValidationException longitudeOutOfBounds() {
            return new CoordinatesValidationException(
                    "Longitude is out of bounds",
                    ErrorProperty.COORDINATE_OUT_OF_BOUNDS,
                    "longitude"
            );
        }
    }
}
