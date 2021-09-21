package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component("paymentService")
@RequiredArgsConstructor
public class LocalPaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;

    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
    }

}
