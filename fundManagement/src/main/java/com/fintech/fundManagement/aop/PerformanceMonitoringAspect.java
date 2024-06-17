package com.fintech.fundManagement.aop;

import com.fintech.fundManagement.utils.Logger.LoggerSingleton;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private final Logger logger = LoggerSingleton.getInstance().getLogger();

    @Around("execution(* com.fintech.fundManagement.controller.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("API: /" + joinPoint.getSignature().getName() + " executed in " + elapsedTime + " ms");
        }
    }
}
