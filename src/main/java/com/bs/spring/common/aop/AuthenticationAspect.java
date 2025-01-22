package com.bs.spring.common.aop;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.common.error.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;

@Aspect
@Component

public class AuthenticationAspect {
    @Before("execution(* com.bs.spring.memo..save*(..))")
    public void checkAuthentication(JoinPoint joinPoint) {
        //HttpSession정보 가져오기
        HttpSession session=(HttpSession)RequestContextHolder.currentRequestAttributes()
            .resolveReference(RequestAttributes.REFERENCE_SESSION);
        Member loginMember=(Member)session.getAttribute("loginMember");
        if(loginMember==null){
            throw new MyException("로그인 후 이용이 가능합니다.");
        }

    }
}