package pl.training.shop.payments.api;

import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.application.PaymentStatus;

import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> getById(String id);

    ResultPage<Payment> getByStatus(PaymentStatus status, int pageNumber, int pageSize);

}
