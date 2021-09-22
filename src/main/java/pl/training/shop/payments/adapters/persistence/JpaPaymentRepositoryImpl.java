package pl.training.shop.payments.adapters.persistence;

import lombok.Setter;
import org.javamoney.moneta.FastMoney;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JpaPaymentRepositoryImpl implements JpaPaymentRepositoryCustom {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PaymentEntity> getGreaterThen(FastMoney value) {
        return entityManager.createQuery("select p from PaymentEntity p where p.value >= :value", PaymentEntity.class)
                .setParameter("value", value)
                .getResultList();
    }

}
