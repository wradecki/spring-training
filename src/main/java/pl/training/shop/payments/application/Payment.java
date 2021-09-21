package pl.training.shop.payments.application;

import lombok.*;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String id;
    private FastMoney value;
    private Instant timestamp;
    private PaymentStatus status;

}