package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Order(100_000)
@Aspect
@Log
@RequiredArgsConstructor
public class LoggingProxy {

    @Pointcut("@annotation(LogPayments)")
    public void logPayments() {
    }

    @Before("logPayments() && args(paymentRequest)")
    public void log(PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "logPayments()", returning = "payment")
    public void log(Payment payment) {
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

    public void init() {
        log.info("Initializing logging proxy");
    }

    public void destroy() {
        log.info("Shutting down logging proxy");
    }

}
