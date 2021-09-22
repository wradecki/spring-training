package pl.training.shop.payments.adapters.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.adapters.persistence.PaymentEntity;
import pl.training.shop.payments.application.PaymentFailedException;
import pl.training.shop.payments.application.PaymentRequest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Order(100_000)
@Component
@Aspect
@Log
@RequiredArgsConstructor
public class LoggingProxy {

    @Pointcut("@annotation(pl.training.shop.payments.application.LogPayments)")
    public void logPayments() {
    }

    @Before("logPayments() && args(paymentRequest)")
    public void log(PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "logPayments()", returning = "payment")
    public void log(PaymentEntity payment) {
        log.info("New payment created: " + payment);
    }

    @AfterThrowing(value = "logPayments()", throwing = "exception")
    public void log(PaymentFailedException exception) {
        log.info("Payment failed: " + exception);
    }

    @After("logPayments()")
    public void log(JoinPoint joinPoint) {
        //joinPoint.getSignature();
        log.info("After payment");
    }

    @PostConstruct
    public void init() {
        log.info("Initializing logging proxy");
    }

    @PreDestroy
    public void destroy() {
        log.info("Shutting down logging proxy");
    }

}
