package com.bs.spring.demo.controller;

import com.bs.spring.demo.model.dto.Address;
import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.view.MyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoController {
    @RequestMapping("/demo/demo.do")
    public String demo(@ModelAttribute("demo") Demo demo) {
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


    //파라미터와 연겨되는 매개변수 추가 서정하기.
    //명칭이 다를 때 연결, 기본값 설정, 피수값 설정

    @RequestMapping("/demo/demo3.do")
    public String demo3(
            @RequestParam(value = "devName") String name,
            @RequestParam(value = "devAge", defaultValue = "19") int Age, // 데이터가 없을때 기본값으로 처리함.
            @RequestParam(value = "devEmail") String email,
            @RequestParam(required = false)String devGender,  // 체크하지 않을 수 있다. 있으면 가져오고 없으면 안가져와도 댐
            @RequestParam(required = false)String[] devLang,
            Model model) {

        System.out.println(name);
        System.out.println(Age);
        System.out.println(email);
        System.out.println(devGender);
        System.out.println(Arrays.toString(devLang));


        return "demo/demoResult";
    }


    //파라미터를 DTO로 바로 저장하기=커맨드
    //파라미터의 key와 DTO의 필드명이 일치하는 값만 저장.
    //DTO에 필드에 다른 크래스가 있으면 저장할 수 없다.
    //@ModelAttribute어노테이션 -> Model에 바로 저장해줌
    @RequestMapping("/demo/demo4.do")
    public String demo4(@ModelAttribute("d") Demo demo, Address address) {   // 파라미터 다 가져와야 했는데 이렇게 DTO로 바로 가져온다.

        demo.setAddress(address);

        System.out.println(demo);
        return "demo/demoResult";

    }

    //파라미터를 Map으로 저장하기

    @RequestMapping("/demo/demo5.do")
    public String demo5(@RequestParam Map param,
                        String[] devLang,
                        Model model) {

        param.put("devLang", devLang);
        System.out.println(param);

        model.addAttribute("demo",param);

        return "demo/demoResult";
    }


    //추가정보 가져오기
    //Session값, Cookie값, Header값 가져오기
    //@SessionAttribute(value="key") -> session
    //@CookieValue(value="key") -> Cookie
    //@RequestHeader(value="key") - > RequestHeader

    @RequestMapping("/demo/demo6.do")
    public String demo6(Demo demo,
                        @SessionAttribute(value = "sessionId", required = false) String id,
                        @CookieValue(value = "lunch") String menu,
                        @RequestHeader(value="Accept") String accept) {

        System.out.println(id);
        System.out.println(menu);
        System.out.println(accept);
        return "demo/demoResult";
    }



    //매핑 메소드 리턴
    //String -> viewResolver가 처리하는 대로 화면을 출력
    //ModelAndView -> Model, view 정보를 한번에 저장하는 객체.
    @RequestMapping("/demo/demo7.do")
    public ModelAndView demo7(Demo demo, ModelAndView mv) {
        //데이터 저장하기
        mv.addObject("demo",demo);

        //view 설정하기
        mv.setViewName("demo/demoResult");

        //매개변수가 아닌 방식으로 불러오기도 가능.
        ModelAndView mv1=new ModelAndView("demo/demoResult", "demo", demo);

        //view 정보확인하기
        System.out.println(mv1.getViewName());
        //model 정보확인하기
        Map<String,Object> modelData=mv1.getModelMap();
        System.out.println(modelData);

        return mv;
    }


    //리턴값으로 객체설정하기 -> 데이터만 전송.
    //@ResponsBody
    //ajax 요청처리, RestAPI로 서비스를 구성할 떄 사용
    @RequestMapping("/demo/demo8.do")
    @ResponseBody //VR이 아니라 데이터에 집어 넣는다 ?
//    public String returnObj(){
//        return "유병승,김통통,우감자,양커비";
//
//    }
    public Demo returnObj(){
        return Demo.builder().devName("test").build();
    }

    @RequestMapping("/demo/demo9.do")
    @ResponseBody
    //json 방식의 데이터를 Demo방식으로 파싱해줌.
    public Demo testOjb(@RequestBody Demo demo) {
        System.out.println(demo);
        return demo;
    }

    //View 구현체로 응답하기

    @Autowired
    private  MyView myView;

    @RequestMapping("/demo/demo10.do")
    public View myViewTest(MyView view, Model model) {
        model.addAttribute("test","나의 view");
//      return myView; 이것도 가능. 위에서 받아서.
        return view;
    }
}
