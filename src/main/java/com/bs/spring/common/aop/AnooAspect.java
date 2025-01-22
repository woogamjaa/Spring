package com.bs.spring.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AnnoAspect {
    //pointcut 설정 -> @Pointcut("표현식")
    //advice 설정 -> @Before||@After||@Around....
    @Pointcut("execution(* com.bs.spring..*(..))")
    public void testall(){}

    @Before("testall()")
    public void before(JoinPoint joinPoint){
        log.debug("=== anno AOP before ===");
        Signature sig = joinPoint.getSignature();
        log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
        //메소드에 전달되는 매개변수 확인하기
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length > 0){
            Arrays.stream(args)
                    .forEach(d->log.debug("매개변수 : {} ",d));
        }


        log.debug("========================================");

    }

        @Pointcut("execution(* com.bs.spring..dao.save*(..))")
        public void daoSave(){}

        @After("daoSave()")
        public void after(JoinPoint joinPoint){
        log.debug("=== anno AOP after ===");
        Signature sig = joinPoint.getSignature();
        log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length > 0){
            Arrays.stream(args)
                    .forEach(d->log.debug("매개변수 : {} ",d));

        }
            log.debug("========================================");
        }

//        @Around("execution(* com.bs.spring.member..*(..))")
        //within(com.bs.spring.common.어노테이션명+) -> 특정 어노테이션이 있는 메소드
        @Around("within(com.bs.spring..dao.*)")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
            //전 , 후에 로직을 실행
            //전, 후 로직은 메소드 내부에 선언
            //전 로직, 후 로직을 구분하는 기준 -> joinPoint.proceed()메소드호출
            log.debug("=== around 전 로직 ===");
            Object o=joinPoint.proceed();
            log.debug("=== around 후 로직 ===");
            return o;
        }
//    @After("testall()")
}
