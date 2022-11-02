package pl.piasta.camperoo.order.domain.vo;

import lombok.AccessLevel;
import lombok.experimental.StandardException;
import pl.piasta.camperoo.common.domain.vo.ValueObject;
import pl.piasta.camperoo.common.exception.ValidationException;

@RequiredArgsConstructor(staticName = "of")
public record Coordinates(Double latitude, Double longitude) implements ValueObject {

    public static final double LATITUDE_BOUND = 90;
    public static final double LONGITUDE_BOUND = 180;

    public Coordinates {
        if (Math.ceil(Math.abs(latitude)) > LATITUDE_BOUND) {
            throw CoordinatesValidationException.latitudeOutOfBounds();
        }
        if (Math.ceil(Math.abs(longitude)) > LONGITUDE_BOUND) {
            throw CoordinatesValidationException.longitudeOutOfBounds();
        }
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
