package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class LoggingProxy {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    public void log(Payment payment) {
        var entry = String.format(LOG_FORMAT, payment.getValue());
        log.info(entry);
    }

    public void init() {
        log.info("Initializing logging proxy");
    }

    public void destroy() {
        log.info("Shutting down logging proxy");
    }

}
