package pl.training.shop.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.application.PaymentEvent;

@Component
@Aspect
@RequiredArgsConstructor
public class PaymentEventsPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Pointcut("@annotation(pl.training.shop.payments.application.LogPayments)")
    public void logPayments() {
    }

    @AfterReturning(value = "logPayments()", returning = "payment")
    public void send(Payment payment) {
        eventPublisher.publishEvent(new PaymentEvent(payment));
    }

}
