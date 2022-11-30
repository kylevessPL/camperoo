package pl.piasta.camperoo.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pl.piasta.camperoo.common.domain.vo.Coordinates.LATITUDE_MAX_VALUE;
import static pl.piasta.camperoo.common.domain.vo.Coordinates.LATITUDE_MIN_VALUE;
import static pl.piasta.camperoo.common.validation.LatitudeCheck.MESSAGE;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@DecimalMin(value = LATITUDE_MIN_VALUE, message = MESSAGE)
@DecimalMax(value = LATITUDE_MAX_VALUE, message = MESSAGE)
public @interface LatitudeCheck {
    String MESSAGE = "{validation.latitude.invalid}";

    String message() default MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
