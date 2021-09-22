package pl.training.shop.payments.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.training.shop.payments.api.PaymentService;

import static pl.training.shop.payments.application.PaymentStatus.STARTED;

@Controller
@RequiredArgsConstructor
public class PaymentsWebController {

    private final PaymentService paymentService;

    @GetMapping("payments.html")
    public ModelAndView showIndex() {
        var modelAndView = new ModelAndView("payments");
        var resultPage = paymentService.getPaymentsByStatus(STARTED, 0, 100);
        modelAndView.addObject(resultPage);
        return modelAndView;
    }

}
