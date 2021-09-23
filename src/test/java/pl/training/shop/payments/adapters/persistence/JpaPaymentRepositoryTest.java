package pl.training.shop.payments.adapters.persistence;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.training.shop.commons.money.LocalMoney;
import pl.training.shop.payments.application.PaymentStatus;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class JpaPaymentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private JpaPaymentRepository sut;

    private static final FastMoney VALUE = LocalMoney.of(1_000);
    private static final PaymentEntity COMPLETED_PAYMENT = PaymentEntity.builder()
            .id("1")
            .status(PaymentStatus.CONFIRMED.name())
            .timestamp(Instant.now())
            .value(VALUE)
            .build();

    @BeforeEach
    void beforeEach() {
        entityManager.persist(COMPLETED_PAYMENT);
        entityManager.flush();
    }

    @Test
    void given_confirmed_payment_in_database_when_select_completed_payments_the_returns_the_payment() {
        var actual = sut.getCompletedWithValue(VALUE);
        assertTrue(actual.contains(COMPLETED_PAYMENT));
    }

}