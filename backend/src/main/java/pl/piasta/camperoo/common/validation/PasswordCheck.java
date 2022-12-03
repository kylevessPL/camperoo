package pl.piasta.camperoo.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pl.piasta.camperoo.security.domain.vo.Password.LENGTH_MAX;
import static pl.piasta.camperoo.security.domain.vo.Password.LENGTH_MIN;
import static pl.piasta.camperoo.security.domain.vo.Password.REGEX;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = LENGTH_MIN, max = LENGTH_MAX)
@Pattern(regexp = REGEX)
@ReportAsSingleViolation
public @interface PasswordCheck {
    String message() default "{validation.password}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
