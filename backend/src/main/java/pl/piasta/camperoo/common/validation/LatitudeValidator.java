package pl.piasta.camperoo.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class LatitudeValidator implements ConstraintValidator<Latitude, Double> {

    public static final double LATITUDE_BOUND = 90;

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return isNull(value) || Math.ceil(Math.abs(value)) <= LATITUDE_BOUND;
    }
}
