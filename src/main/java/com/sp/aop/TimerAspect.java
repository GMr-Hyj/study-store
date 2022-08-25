package com.sp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {
    @Around("execution(* com.sp.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 记录当前时间
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        // 后记录当前时间
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-start));
        return result;
    }
}
