package pl.training.shop.payments;

import lombok.*;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;

// https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
@Table(name = "payments")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    //@Convert(converter = FastMoneyConverter.class)
    private FastMoney money;
    private Instant timestamp;
    @Enumerated(STRING)
    private PaymentStatus status;

    @Override
    public boolean equals(Object otherPayment) {
        if (this == otherPayment) {
            return true;
        }
        if (otherPayment == null || getClass() != otherPayment.getClass()) {
            return false;
        }
        var payment = (Payment) otherPayment;
        return Objects.equals(id, payment.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
