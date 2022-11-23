package pl.piasta.camperoo.common.util;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.util.ServletRequestPathUtils;
import pl.piasta.camperoo.common.exception.AppException;
import pl.piasta.camperoo.common.exception.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ErrorHandlingUtils {
    public String resolveRequestPath(WebRequest request) {
        var servletRequest = ((ServletRequestAttributes) request).getRequest();
        return resolveRequestPath(servletRequest);
    }

    public String resolveRequestPath(HttpServletRequest request) {
        return ServletRequestPathUtils.hasCachedPath(request)
               ? ServletRequestPathUtils.getCachedPathValue(request)
               : request.getRequestURI();
    }

    public ErrorAttributes createErrorAttributes(Throwable ex, HttpStatus status, String path) {
        return createErrorAttributes(ex, status, path, null);
    }

    public ErrorAttributes createErrorAttributes(
            Throwable ex,
            HttpStatus status,
            String path,
            MessageSource messageSource
    ) {
        var error = ExceptionUtils.getRootCause(ex);
        var errors = createErrorDetails(error, messageSource);
        var message = CollectionUtils.isEmpty(errors) ? error.getMessage() : null;
        return ErrorAttributes.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .errors(errors)
                .message(message)
                .path(path)
                .build();
    }

    private Set<SingleError> createErrorDetails(Throwable ex, MessageSource messageSource) {
        return switch (ex) {
            case BindException error -> validationError(error);
            case ConstraintViolationException error -> validationError(error);
            case AppException error -> appError(error, messageSource);
            case DataAccessException ignored -> error(ErrorCode.DATABASE_ERROR, "Database error has occurred");
            case MaxUploadSizeExceededException ignored -> error(ErrorCode.FILE_ERROR, "File is too large");
            default -> null;
        };
    }

    private Set<SingleError> appError(AppException ex, MessageSource messageSource) {
        var message = messageSource.getMessage(ex, LocaleContextHolder.getLocale());
        return error(ex.getCode(), message);
    }

    private Set<SingleError> error(ErrorCode key, String message) {
        return Set.of(new SingleError(key, null, message));
    }

    private Set<SingleError> validationError(BindException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new SingleError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toSet());
    }

    private Set<SingleError> validationError(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(error -> new SingleError(getConstraintViolationField(error), error.getMessage()))
                .collect(Collectors.toSet());
    }

    private String getConstraintViolationField(ConstraintViolation<?> constraintViolation) {
        var field = constraintViolation.getPropertyPath().toString();
        return field.substring(field.indexOf('.') + 1);
    }

    @Getter
    @Builder
    @Jacksonized
    private class ErrorAttributes {
        @Builder.Default
        @JsonInstant
        private Instant timestamp = Instant.now();
        private int status;
        private String error;
        private String message;
        @Builder.Default
        private Set<SingleError> errors = new HashSet<>();
        private String path;
    }

    private record SingleError(ErrorCode key, String field, String message) {
        SingleError(String field, String message) {
            this(null, field, message);
        }
    }
}
