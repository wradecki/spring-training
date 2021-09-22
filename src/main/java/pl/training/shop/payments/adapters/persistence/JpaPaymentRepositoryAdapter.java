package pl.training.shop.payments.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.payments.api.PaymentRepository;
import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.application.PaymentStatus;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentModelMapper mapper;
    private final JpaPaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        var paymentEntity = repository.save(mapper.toEntity(payment));
        return mapper.toDomain(paymentEntity);
    }

    @Override
    public Optional<Payment> getById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, int pageNumber, int pageSize) {
        var page = repository.getByStatus(status.name(), PageRequest.of(pageNumber, pageSize));
        var data = mapper.toDomain(page.getContent());
        return new ResultPage<>(data, pageNumber, page.getTotalPages());
    }

}
