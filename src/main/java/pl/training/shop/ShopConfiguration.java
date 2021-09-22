package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.api.PaymentService;
import pl.training.shop.payments.application.PaymentsServiceFactory;

@Configuration
public class ShopConfiguration implements WebMvcConfigurer {

    private final PaymentsServiceFactory paymentsServiceFactory = new PaymentsServiceFactory();

    @Bean
    public PaymentService paymentService(PaymentRepository paymentRepository) {
        return paymentsServiceFactory.create(paymentRepository);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("index.html").setViewName("index");
    }

}
