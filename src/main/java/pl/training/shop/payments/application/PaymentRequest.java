package pl.training.shop.payments.application;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Builder
@Value
public class PaymentRequest {

    Long id;
    FastMoney money;

}
