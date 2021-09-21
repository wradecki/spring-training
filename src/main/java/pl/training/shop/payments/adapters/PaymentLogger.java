package pl.training.shop.payments.adapters;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.application.PaymentEvent;

@Component
@Log
public class PaymentLogger {

    @Async
    @EventListener
    public void onPaymentEvent(PaymentEvent paymentEvent) {
        log.info("New payment event: " + paymentEvent.getPayment());
    }

}
