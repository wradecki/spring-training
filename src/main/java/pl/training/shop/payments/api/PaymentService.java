package pl.training.shop.payments.api;

import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.application.PaymentRequest;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

}
