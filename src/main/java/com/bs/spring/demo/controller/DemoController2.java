package com.bs.spring.demo.controller;

import com.bs.spring.demo.model.dto.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demo")   //
public class DemoController2 {
    //요청을 매핑하는 어노테이션
    //@RequestMapping(value="url", params="", headers="" ,consumes="",produces="")
    @RequestMapping(value = "/request/test",
            method = RequestMethod.GET,
            params = {"accesskey", "test=유병승", "!data"}, // 파라미터로 넘어오는 값들 중에서 accesskey 가 있어야 실행.
            consumes = "aplplication/json"
    )

    public String test() {
        System.out.println("test메소드 실행.");
        return "/";
    }

    //요청 메소드에 따른 매핑
    //@GetMapping == @RequestMapping(value="",method=RequestMethod.GET)
    @GetMapping("test2")
    public String test2() {

        System.out.println("test2실행");

        //snedRedirect로 전환.
        return "redirect:/"; //
    }


    //@PostMapping
    @PostMapping(value = "/test3")
    public String test3() {
        System.out.println("test3실행");
        return "redirect:/";
    }

    //@PutMapping
    //@PatchMapping
    //@DeleteMapping

    @RequestMapping("/insertdemo.do")
    public String insertdemo(@ModelAttribute Demo demo) {
        //DB mybatis
        //1. mybatis.jar
        //2. mybatis설정파일 mybatis-config, mapper.xml
        //3. SqlSession -> DataSource
        return "";
    }


}
