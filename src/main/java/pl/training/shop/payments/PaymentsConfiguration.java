package pl.training.shop.payments;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentsConfiguration {

    //@Scope(SCOPE_SINGLETON)
    @Bean("incremental")
    public PaymentIdGenerator incrementalPaymentIdGenerator() {
        return new IncrementalPaymentIdGenerator();
    }

    @Bean("uuid")
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentsRepository() {
        return new JpaPaymentRepository();
    }

    @Bean
    public PaymentService localPaymentService(@Generator("uuid") PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
        return new LocalPaymentService(paymentIdGenerator, paymentRepository);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LoggingProxy loggingProxy() {
        return new LoggingProxy();
    }

    @Bean
    public PaymentEventsPublisher paymentEventsPublisher(ApplicationEventPublisher eventPublisher) {
        return new PaymentEventsPublisher(eventPublisher);
    }

    @Bean
    public PaymentEventsListener paymentEventsListener() {
       return new PaymentEventsListener();
    }

}
