package pl.training.shop;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.payments.adapters.LocalMoney;
import pl.training.shop.payments.application.PaymentRequest;
import pl.training.shop.payments.api.PaymentService;

@Log
public class Application {

    private static final String BASE_PACKAGE = "pl.training.shop";

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
            var paymentService = context.getBean(PaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(LocalMoney.of(1_000))
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }
    }

}
