package com.bs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {
    @RequestMapping("/")
    public String index() {
        System.out.println("index메소드 실행");
        return "index";
    }
}
