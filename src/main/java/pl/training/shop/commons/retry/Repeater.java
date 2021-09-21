package pl.training.shop.commons.retry;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

@Aspect
@Log
public class Repeater {

    @Around("@annotation(Retry)")
    public Object tryExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        var annotation = method.getAnnotation(Retry.class);
        if (annotation == null) {
            throw new IllegalStateException();
        }
        var currentAttempt = 0;
        var attempts = annotation.attempts();
        Throwable throwable;
        do {
            currentAttempt++;
            log.info(String.format("%s method execution attempt %d", method.getName(), currentAttempt));
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable exception) {
                throwable = exception;
            }
        } while (currentAttempt < attempts - 1);
        throw throwable;
    }

}
