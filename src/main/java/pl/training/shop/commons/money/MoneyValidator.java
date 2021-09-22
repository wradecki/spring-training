package pl.training.shop.commons.money;

import org.javamoney.moneta.FastMoney;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoneyValidator implements ConstraintValidator<Money, String> {

    private long minValue;

    @Override
    public void initialize(Money constraintAnnotation) {
        minValue = constraintAnnotation.minValue();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        var result = false;
        try {
            result = FastMoney.parse(value).isGreaterThanOrEqualTo(minValue);
        } catch (Exception exception) {
            result = false;
        }
        return result;
    }

}
