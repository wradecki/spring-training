package pl.training.shop;

import lombok.extern.java.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.shop.payments.LocalMoney;
import pl.training.shop.payments.PaymentRequest;
import pl.training.shop.payments.PaymentService;

@Log
public class Application {

    private static final String CONFIGURATION_FILE = "beans.xml";

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIGURATION_FILE)) {
            var paymentService = context.getBean(PaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(LocalMoney.of(1_000))
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }
    }

}
