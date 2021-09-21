package pl.training.shop.payments.adapters;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.application.PaymentStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static pl.training.shop.payments.adapters.PaymentEntity.SELECT_BY_STATUS;

@Repository
@RequiredArgsConstructor
public class JpaPaymentRepository implements PaymentRepository {

    private final JpaPaymentModelMapper mapper;

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment save(Payment payment) {
        var paymentEntity = mapper.toEntity(payment);
        entityManager.persist(paymentEntity);
        return mapper.toDomain(paymentEntity);
    }

    @Override
    public Optional<Payment> getById(String id) {
        var payment = entityManager.find(PaymentEntity.class, id);
        return Optional.ofNullable(payment)
                .map(mapper::toDomain);
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, int pageNumber, int pageSize) {
        var data = entityManager.createNamedQuery(SELECT_BY_STATUS, PaymentEntity.class)
                .setParameter("status", status)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new ResultPage<>(mapper.toDomain(data), pageNumber, -1);
    }

}
