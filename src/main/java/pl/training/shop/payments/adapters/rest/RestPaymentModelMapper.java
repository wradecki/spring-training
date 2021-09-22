package pl.training.shop.payments.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import pl.training.shop.commons.money.FastMoneyModelMapper;
import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.application.PaymentRequest;
import pl.training.shop.payments.application.PaymentStatus;

import java.util.List;

@Mapper(componentModel = "spring", uses = FastMoneyModelMapper.class)
public interface RestPaymentModelMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequest toDomain(PaymentRequestDto paymentRequestDto);

    @ValueMapping(source = "STARTED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "FAILED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "CANCELED", target = "NOT_CONFIRMED")
    PaymentStatusDto toDto(PaymentStatus paymentStatus);

    PaymentDto toDto(Payment payment);

    @IterableMapping(elementTargetType = PaymentDto.class)
    List<PaymentDto> toDto(List<Payment> payments);

}
