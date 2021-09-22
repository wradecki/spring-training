package pl.training.shop.commons.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class GlobalExceptionHandler {

    protected final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        exception.printStackTrace(); // for development only
        return createResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionDto> onValidationException(MethodArgumentNotValidException exception) {
        var description = "Validation failed - " + getValidationErrors(exception);
        return ResponseEntity.badRequest().body(new ExceptionDto(description));
    }

    private String getValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(joining(", "));
    }

    protected ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus status, Locale locale) {
        var description = getDescription(exception.getClass().getSimpleName(), locale);
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

    private String getDescription(String key, Locale locale) {
        var description = key;
        try {
            description = messageSource.getMessage(key, new Object[]{}, locale);
        } catch (NoSuchMessageException exception) {
            description = "Unknown exception";
        }
        return description;
    }
}
