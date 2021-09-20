package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;

@Aspect
@RequiredArgsConstructor
public class PaymentEventsPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Pointcut("@annotation(LogPayments)")
    public void logPayments() {
    }

    @AfterReturning(value = "logPayments()", returning = "payment")
    public void send(Payment payment) {
        eventPublisher.publishEvent(new PaymentEvent(payment));
    }

}
