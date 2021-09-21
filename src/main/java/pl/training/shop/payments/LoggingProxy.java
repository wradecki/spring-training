package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Aspect
@Component
@Log
@RequiredArgsConstructor
public class LoggingProxy {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void log(Payment payment) {
        var entry = String.format(LOG_FORMAT, payment.getValue());
        log.info(entry);
    }

    @PostConstruct
    public void init() {
        log.info("Initializing logging proxy");
    }

    @PreDestroy
    public void destroy() {
        log.info("Shutting down logging proxy");
    }

}
