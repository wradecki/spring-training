package pl.training.shop.payments.adapters.rest;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.training.shop.commons.money.LocalMoney;
import pl.training.shop.payments.api.PaymentService;
import pl.training.shop.payments.application.Payment;
import pl.training.shop.payments.application.PaymentStatus;

import java.time.Instant;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@WebMvcTest(PaymentRestController.class)
@ExtendWith(SpringExtension.class)
class PaymentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PaymentService paymentService;
    @MockBean
    private RestPaymentModelMapper mapper;

    private static final String PAYMENT_ID = "1";
    private static final FastMoney VALUE = LocalMoney.of(1_000);
    private static final Payment PAYMENT = Payment.builder()
            .id(PAYMENT_ID)
            .value(VALUE)
            .status(PaymentStatus.STARTED)
            .timestamp(Instant.now())
            .build();

    @WithMockUser(username = "user", authorities = "ROLE_USER")
    @BeforeEach
    void beforeEach() {
        when(paymentService.getById(PAYMENT_ID)).thenReturn(PAYMENT);
        when(mapper.toDto(any(Payment.class))).then(invocation -> {
            var payment = invocation.getArgument(0, Payment.class);
            var dto = new PaymentDto();
            dto.setId(payment.getId());
            dto.setValue(payment.getValue().toString());
            return dto;
        });
    }

    @Test
    void given_payment_when_get_by_id_then_returns_the_payment() throws Exception {
        mockMvc.perform(get("/api/payments/" + PAYMENT_ID)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(PAYMENT_ID)))
                .andExpect(jsonPath("$.value", is(VALUE.toString())));
    }

}