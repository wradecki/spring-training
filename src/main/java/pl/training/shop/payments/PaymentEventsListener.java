package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Log
public class PaymentEventsListener {

    @Async
    @EventListener
    public void onPaymentEvent(PaymentEvent paymentEvent) {
        log.info("New payment event: " + paymentEvent.getPayment());
    }

}
