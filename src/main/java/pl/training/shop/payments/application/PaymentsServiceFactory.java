package pl.training.shop.payments.application;

import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.api.PaymentService;

public class PaymentsServiceFactory {

    private static final PaymentIdGenerator PAYMENT_ID_GENERATOR = new UUIDPaymentIdGenerator();

    public PaymentService create(PaymentRepository paymentRepository) {
        return new LocalPaymentService(PAYMENT_ID_GENERATOR, paymentRepository);
    }

}
