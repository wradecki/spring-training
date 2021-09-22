package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.commons.web.ExceptionDto;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.commons.web.UriBuilder;
import pl.training.shop.payments.api.PaymentService;
import pl.training.shop.payments.application.PaymentNotFoundException;
import pl.training.shop.payments.application.PaymentStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pl.training.shop.payments.application.PaymentStatus.CONFIRMED;
import static pl.training.shop.payments.application.PaymentStatus.STARTED;

@RequestMapping(value = "payments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;
    private final RestPaymentModelMapper mapper;

    @PostMapping
    public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = mapper.toDomain(paymentRequestDto);
        var payment = paymentService.process(paymentRequest);
        var paymentDto = mapper.toDto(payment);
        var locationUri = UriBuilder.requestUriWithId(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

    @GetMapping("{id}")
    public PaymentDto getPaymentById(@PathVariable String id) {
        var payment = paymentService.getById(id);
        return mapper.toDto(payment);
    }

    @GetMapping("started")
    public ResultPageDto<PaymentDto> getPayments(@RequestParam int pageNumber, @RequestParam(required = false, defaultValue = "5") int pageSize) {
        var resultPage = paymentService.getPaymentsByStatus(STARTED, pageNumber, pageSize);
        var paymentsDtos = mapper.toDto(resultPage.getData());
        return new ResultPageDto<>(paymentsDtos, pageNumber, resultPage.getTotalPages());
    }

    /*@ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDto onPaymentNotFound(PaymentNotFoundException exception) {
        return new ExceptionDto("Payment not found");
    }*/

}
