package pl.piasta.camperoo.order.domain.vo;

import lombok.AccessLevel;
import lombok.experimental.StandardException;
import org.springframework.util.CollectionUtils;
import pl.piasta.camperoo.common.domain.vo.ValueObject;
import pl.piasta.camperoo.common.exception.ValidationException;

import java.util.List;

@RequiredArgsConstructor(staticName = "of")
public record RouteDistance(int distance, List<String> countryCodes) implements ValueObject {

    public RouteDistance {
        if (distance < 0) {
            throw RouteDistanceValidationException.distanceNegative();
        }
        if (CollectionUtils.isEmpty(countryCodes)) {
            throw RouteDistanceValidationException.countryCodesEmpty();
        }
    }

    @StandardException(access = AccessLevel.PRIVATE)
    private static class RouteDistanceValidationException extends ValidationException {

        public static RouteDistanceValidationException distanceNegative() {
            return new RouteDistanceValidationException("Distance cannot be a negative value");
        }

        public static RouteDistanceValidationException countryCodesEmpty() {
            return new RouteDistanceValidationException("Country codes cannot be empty");
        }
    }
}
