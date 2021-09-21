package pl.training.shop.commons.profiler;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(1_000)
@Aspect
@Log
public class Profiler implements Ordered {

    @Around("execution(* pl.training.shop.payments.application.LocalPaymentService.*(..))")
    //@Around("bean(localPaymentService)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = proceedingJoinPoint.proceed();
        var totalTime = System.nanoTime() - startTime;
        log.info(String.format("%s executed in %d ns", proceedingJoinPoint.getSignature(), totalTime));
        return result;
    }

    @Override
    public int getOrder() {
        return 1_000;
    }

}
