package pl.training.shop.payments.adapters.rest;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pl.training.shop.Application;
import pl.training.shop.commons.money.LocalMoney;
import pl.training.shop.payments.adapters.persistence.PaymentEntity;
import pl.training.shop.payments.application.PaymentStatus;

import javax.persistence.EntityManager;
import java.time.Instant;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class PaymentRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;

    private static final String PAYMENT_ID = "1";
    private static final FastMoney VALUE = LocalMoney.of(1_000);
    private static final PaymentEntity COMPLETED_PAYMENT = PaymentEntity.builder()
            .id("1")
            .status(PaymentStatus.CONFIRMED.name())
            .timestamp(Instant.now())
            .value(VALUE)
            .build();


    @Transactional
    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void given_payment_when_get_by_id_then_returns_the_payment() throws Exception {
        entityManager.persist(COMPLETED_PAYMENT);
        entityManager.flush();
        mockMvc.perform(get("/api/payments/" + PAYMENT_ID)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(PAYMENT_ID)))
                .andExpect(jsonPath("$.value", is(VALUE.toString())));
    }

}