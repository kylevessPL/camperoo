package pl.piasta.camperoo.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pl.piasta.camperoo.common.validation.AlphaNumericCheck.REGEX;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = REGEX)
@ReportAsSingleViolation
public @interface AlphaNumericCheck {
    String REGEX = "^[\\w\\-/, ]*$";

    String message() default "{validation.alphaNumeric}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
