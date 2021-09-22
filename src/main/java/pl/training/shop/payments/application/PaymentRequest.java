package pl.training.shop.payments.application;

import lombok.*;
import org.javamoney.moneta.FastMoney;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private Long id;
    private FastMoney value;

}
