package pl.training.shop.payments;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Generator("uuid")
//@Scope(SCOPE_SINGLETON)
//@Component
public class UUIDPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
