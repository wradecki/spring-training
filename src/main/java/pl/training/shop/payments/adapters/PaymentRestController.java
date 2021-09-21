package pl.training.shop.payments.adapters;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

    @GetMapping(value = "/sayHello", produces = MediaType.TEXT_PLAIN_VALUE)
    //@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String hello() {
        return "Hello";
    }

}
