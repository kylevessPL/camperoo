package pl.piasta.camperoo.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class LongitudeValidator implements ConstraintValidator<Longitude, Double> {

    public static final double LONGITUDE_BOUND = 180;

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return isNull(value) || Math.ceil(Math.abs(value)) <= LONGITUDE_BOUND;
    }
}
