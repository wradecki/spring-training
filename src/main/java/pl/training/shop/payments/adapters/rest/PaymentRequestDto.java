package pl.training.shop.payments.adapters.rest;

import lombok.Data;
import pl.training.shop.commons.BaseValidation;
import pl.training.shop.commons.ExtendedValidation;
import pl.training.shop.commons.money.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PaymentRequestDto {

    @Min(value = 1, groups = BaseValidation.class)
    private Long requestId;
    @Pattern(regexp = "\\d+ PLN", groups = BaseValidation.class)
    @NotNull(groups = BaseValidation.class)
    @Money(minValue = 1, groups = ExtendedValidation.class)
    private String value;

}
