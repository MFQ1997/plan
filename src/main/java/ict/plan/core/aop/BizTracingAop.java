package ict.plan.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BizTracingAop {

    @Pointcut("execution(* ict.plan.service..*.*(..))")
    public void executeService(){}

    @AfterReturning(pointcut = "executeService()", returning = "target")
    public void afterReturning(JoinPoint joinPoint, Object target){
    }

    @AfterThrowing(pointcut = "executeService()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) throws Exception{
    }
}
