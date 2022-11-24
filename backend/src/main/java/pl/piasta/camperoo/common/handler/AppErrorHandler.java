package pl.piasta.camperoo.common.handler;

import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.piasta.camperoo.common.exception.BusinessException;
import pl.piasta.camperoo.common.exception.DuplicateException;
import pl.piasta.camperoo.common.exception.NotFoundException;
import pl.piasta.camperoo.common.exception.ValidationException;
import pl.piasta.camperoo.common.util.ErrorHandlingUtils;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Setter(onMethod_ = @Override, onParam_ = @NonNull)
class AppErrorHandler extends ResponseEntityExceptionHandler implements MessageSourceAware {
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundError(Exception ex, WebRequest request) {
        return sendError(ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<Object> handleDuplicateError(Exception ex, WebRequest request) {
        return sendError(ex, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessError(Exception ex, WebRequest request) {
        return sendError(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ValidationException.class, ConstraintViolationException.class})
    protected ResponseEntity<Object> handleValidationError(Exception ex, WebRequest request) {
        return sendError(ex, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<Object> handleFileSizeLimitExceededError(Exception ex, WebRequest request) {
        return sendError(ex, new HttpHeaders(), HttpStatus.PAYLOAD_TOO_LARGE, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAuthorizationError(Exception ex, WebRequest request) {
        return sendError(ex, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessError(Exception ex, WebRequest request) {
        return sendError(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        try {
            return super.handleException(ex, request);
        } catch (Exception e) {
            return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request
    ) {
        return handleValidationError(ex, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request
    ) {
        return handleValidationError(ex, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleBindException(
            @NonNull BindException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request
    ) {
        return handleValidationError(ex, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {
        return sendError(ex, headers, status, request);
    }

    private ResponseEntity<Object> sendError(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn(ex.getMessage(), ex);
        var path = ErrorHandlingUtils.resolveRequestPath(request);
        var body = ErrorHandlingUtils.createErrorAttributes(ex, status, path, messageSource);
        return new ResponseEntity<>(body, headers, status);
    }
}

