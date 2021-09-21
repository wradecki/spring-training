package pl.training.shop.payments.adapters;

import org.mapstruct.Mapper;
import pl.training.shop.payments.application.Payment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JpaPaymentModelMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toDomain(PaymentEntity paymentEntity);

    List<Payment> toDomain(List<PaymentEntity> paymentEntities);

}
