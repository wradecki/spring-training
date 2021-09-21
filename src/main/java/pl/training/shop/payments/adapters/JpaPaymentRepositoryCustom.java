package pl.training.shop.payments.adapters;

import org.javamoney.moneta.FastMoney;

import java.util.List;

public interface JpaPaymentRepositoryCustom {

    List<PaymentEntity> getGreaterThen(FastMoney value);

}
