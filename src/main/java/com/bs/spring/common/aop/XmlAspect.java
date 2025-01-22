package com.bs.spring.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

@Slf4j
public class XmlAspect {
    //공통의 기능(횡단관심사)을 제공하는메소드 선언
    //메소드는 advice에 따라서 형태가 정해져있음.
    //Before,After,AfterReturning, AfterThrowing
    //public void 메소드명(joinPoint[, Throwable])
    //Around
    //public Object 메소드명(ProceedinJoinPoint)

    public void before(JoinPoint joinPoint) {
        log.debug("=== before ===");
        //실행되는 클래스, 메소드 관련 정보를 확인할 수 있음.
        Signature sig = joinPoint.getSignature();
        log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
        log.debug("========================");

    }

    public void after(JoinPoint joinPoint) {
        log.debug("=== after ===");
        Signature sig = joinPoint.getSignature();
        log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
        log.debug("========================");

    }
}
