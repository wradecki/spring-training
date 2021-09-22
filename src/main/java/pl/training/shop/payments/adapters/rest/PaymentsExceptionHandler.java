package pl.training.shop.payments.adapters.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.training.shop.commons.web.ExceptionDto;
import pl.training.shop.commons.web.GlobalExceptionHandler;
import pl.training.shop.payments.application.PaymentNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice(basePackageClasses = PaymentRestController.class)
public class PaymentsExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception) {
        return createResponse(exception, NOT_FOUND);
    }

}
