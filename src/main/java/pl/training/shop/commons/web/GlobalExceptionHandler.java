package pl.training.shop.commons.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> onException(Exception exception) {
        exception.printStackTrace(); // for development only
        return createResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus status) {
        var description = exception.getClass().getSimpleName();
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

}
