package com.bs.spring.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

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

    }


}
