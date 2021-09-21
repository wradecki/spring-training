package pl.training.shop.payments.adapters;

import org.javamoney.moneta.FastMoney;
import pl.training.shop.payments.application.LocalMoney;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FastMoneyConverter implements AttributeConverter<FastMoney, String> {

    @Override
    public String convertToDatabaseColumn(FastMoney value) {
        return value != null ? value.toString() : null;
    }

    @Override
    public FastMoney convertToEntityAttribute(String value) {
        return value != null ? FastMoney.parse(value) : LocalMoney.zero();
    }

}
