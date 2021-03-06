package pl.training.shop.payments.adapters.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.FastMoney;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

// https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
@Table(name = "payments")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    private String id;
    //@Convert(converter = FastMoneyConverter.class)
    @NotNull
    private FastMoney value;
    private Instant timestamp;
    private String status;

    @Override
    public boolean equals(Object otherPayment) {
        if (this == otherPayment) {
            return true;
        }
        if (otherPayment == null || getClass() != otherPayment.getClass()) {
            return false;
        }
        var payment = (PaymentEntity) otherPayment;
        return Objects.equals(id, payment.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
