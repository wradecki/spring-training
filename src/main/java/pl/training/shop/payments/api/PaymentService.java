package pl.training.shop.payments.api;

import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.application.PaymentRequest;
import pl.training.shop.payments.application.PaymentStatus;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

    Payment getById(String id);

    ResultPage<Payment> getPaymentsByStatus(PaymentStatus status, int pageNumber, int pageSize);

}
