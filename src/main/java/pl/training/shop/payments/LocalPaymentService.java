package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
//@Component("paymentService")
//@RequiredArgsConstructor
public class LocalPaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    @Autowired
    public LocalPaymentService(/*@Qualifier("uuid")*/ @Generator("uuid") PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentRepository = paymentRepository;
    }

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
