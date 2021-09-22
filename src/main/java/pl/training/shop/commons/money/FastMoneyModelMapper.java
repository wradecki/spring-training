package pl.training.shop.commons.money;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class FastMoneyModelMapper {

    public String toText(FastMoney value) {
        return value != null ? value.toString() : "";
    }

    public FastMoney toMoney(String value) {
        return value != null ? FastMoney.parse(value) : LocalMoney.zero();
    }

}
