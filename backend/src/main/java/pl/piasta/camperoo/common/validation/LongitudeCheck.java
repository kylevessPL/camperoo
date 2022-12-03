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

import static pl.piasta.camperoo.common.domain.vo.Coordinates.LONGITUDE_MAX_VALUE;
import static pl.piasta.camperoo.common.domain.vo.Coordinates.LONGITUDE_MIN_VALUE;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@DecimalMin(value = LONGITUDE_MIN_VALUE)
@DecimalMax(value = LONGITUDE_MAX_VALUE)
@ReportAsSingleViolation
public @interface LongitudeCheck {
    String message() default "{validation.longitude}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
