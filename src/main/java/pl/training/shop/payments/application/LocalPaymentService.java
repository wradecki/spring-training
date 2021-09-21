package pl.training.shop.payments.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.api.PaymentService;

import javax.transaction.Transactional;
import java.time.Instant;

@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class LocalPaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        return paymentRepository.save(payment);
    }

}
