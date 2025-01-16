package com.bs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BasicController {
    @RequestMapping("/")
    public String index(HttpSession session, HttpServletResponse response) {

        //session저장
        session.setAttribute("sessionId", "bsyoo");
        //Cookie저장
        Cookie c = new Cookie("lunch", "pizza");
        c.setMaxAge(60*60*24); // 하루동안
        response.addCookie(c);

        System.out.println("index메소드 실행");
        return "index";
    }
}
