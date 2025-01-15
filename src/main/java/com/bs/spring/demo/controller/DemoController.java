package com.bs.spring.demo.controller;

import com.bs.spring.demo.model.dto.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Controller
public class DemoController {
    @RequestMapping("/demo/demo.do")
    public String index() {
        System.out.println("index메소드 실행");
        return "demo/demo";
    }

    //매핑메소드 파라미터 처리하기 -> 매개변수 활용하기.
    //httpServletRequest, httpServletResponse활용하기
    @RequestMapping("/demo/demo1.do")
    public void demo1(HttpServletRequest request,
                        HttpServletResponse response,
                      HttpSession session) throws IOException, ServletException {

        System.out.println(request);
        System.out.println(response);
        String name=request.getParameter("devName");
        int age=Integer.parseInt(request.getParameter("devAge"));
        String gender=request.getParameter("devGender");
        String[] devLang=request.getParameterValues("devLang");

        System.out.println(name);
        System.out.println(age);
        System.out.println(gender);
        System.out.println(Arrays.toString(devLang));

        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(name);
        out.print(age);
        out.print(gender);
        out.print(Arrays.toString(devLang));

        Demo d= Demo.builder()
                .devName(name)
                .devAge(age)
                .devGender(gender)
                .devLang(devLang)
                .build();

        //HttpSession 이용하기
        session.setAttribute("loginMember","admin");


        request.setAttribute("demo",d);
        request.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp")
                .forward(request, response);

    }


    //파라미터값을 매개변수와 매칭하여 받아오기
    //매개변수 선언할때 파라미터의 key값와 동일하게 설정
    //devName, devAge, devEmail, devGender, devLang 파라미터를 전송
    //request.getParameter()메소드를 직접사용하지 않아도 됨 !
    
    //파라미터 데이터가 필수값 (required) 일때 활용
    @RequestMapping("/demo/demo2.do")
    public String demo2(String devName, int devAge, String devEmail, String devGender, String[] devLang, Model model) {

        System.out.println(devName);
        System.out.println(devAge);
        System.out.println(devEmail);
        System.out.println(devGender);
        System.out.println(Arrays.toString(devLang));

        Demo demo=Demo.builder()
                .devName(devName)
                .devAge(devAge)
                .devGender(devGender)
//                .devEmail(devEmail)
                .devLang(devLang)
                .build();

        //model에 저장하기
        model.addAttribute("demo",demo);
        return "demo/demoResult";

    }

}
