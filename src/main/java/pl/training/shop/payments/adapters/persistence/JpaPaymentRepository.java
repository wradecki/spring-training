package pl.training.shop.payments.adapters.persistence;

import org.javamoney.moneta.FastMoney;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, String>, JpaPaymentRepositoryCustom {

    List<PaymentEntity> getByStatus(String status);

    Page<PaymentEntity> getByStatus(String status, Pageable pageable);

    @Query("select p from PaymentEntity p where p.status = 'COMPLETED' and p.value = :value")
    List<PaymentEntity> getCompletedWithValue(@Param("value") FastMoney value);

}
