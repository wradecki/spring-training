package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.api.PaymentService;
import pl.training.shop.payments.application.PaymentsServiceFactory;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ShopConfiguration {

    private final PaymentsServiceFactory paymentsServiceFactory = new PaymentsServiceFactory();

    @Bean
    public PaymentService paymentService(PaymentRepository paymentRepository) {
        return paymentsServiceFactory.create(paymentRepository);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

}
