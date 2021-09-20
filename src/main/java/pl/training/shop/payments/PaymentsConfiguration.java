package pl.training.shop.payments;

import org.springframework.context.annotation.*;

@EnableAspectJAutoProxy
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
    public PaymentRepository hashSetPaymentsRepository() {
        return new HashSetPaymentRepository();
    }

    @Bean
    public PaymentService localPaymentService(@Generator("uuid") PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
        return new LocalPaymentService(paymentIdGenerator, paymentRepository);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LoggingProxy loggingProxy() {
        return new LoggingProxy();
    }

}
