package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.*;

@Aspect
@Log
@RequiredArgsConstructor
public class LoggingProxy {

    @Before("@annotation(LogPayments) && args(paymentRequest)")
    public void log(PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info("New payment created: " + payment);
    }

    @AfterThrowing(value = "@annotation(LogPayments)", throwing = "exception")
    public void log(PaymentFailedException exception) {
        log.info("Payment failed: " + exception);
    }

    @After("@annotation(LogPayments)")
    public void log() {
        log.info("After payment");
    }

    public void init() {
        log.info("Initializing logging proxy");
    }

    public void destroy() {
        log.info("Shutting down logging proxy");
    }

}
