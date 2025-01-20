package com.bs.spring.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BasicController {
    private final Logger log = LogManager.getLogger(BasicController.class);



    @RequestMapping("/")
    public String index(HttpSession session, HttpServletResponse response) {

        //session저장
        session.setAttribute("sessionId", "bsyoo");
        //Cookie저장
        Cookie c = new Cookie("lunch", "pizza");
        c.setMaxAge(60*60*24); // 하루동안
        response.addCookie(c);




//
//
//        System.out.println("index메소드 실행");

        //logger를 이용해서 출력하기
        log.debug("debug로 출력하기");
        log.info("info로 출력하기");
        log.warn("warn로 출력하기");
        log.error("error로 출력하기");



        return "index";
    }
}
