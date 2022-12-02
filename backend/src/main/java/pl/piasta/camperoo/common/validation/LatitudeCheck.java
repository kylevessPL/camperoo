package pl.piasta.camperoo.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pl.piasta.camperoo.common.domain.vo.Coordinates.LATITUDE_MAX_VALUE;
import static pl.piasta.camperoo.common.domain.vo.Coordinates.LATITUDE_MIN_VALUE;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@DecimalMin(value = LATITUDE_MIN_VALUE)
@DecimalMax(value = LATITUDE_MAX_VALUE)
@ReportAsSingleViolation
public @interface LatitudeCheck {
    String message() default "{validation.latitude}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
