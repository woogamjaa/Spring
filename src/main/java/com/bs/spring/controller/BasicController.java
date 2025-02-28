package com.bs.spring.controller;


import com.bs.spring.common.error.MyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class BasicController {

    private final Logger log = LogManager.getLogger(BasicController.class);

    @Autowired
    private    WebApplicationContext webcontext;

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

    @RequestMapping("/messagetest/{locale}")
    @ResponseBody
    public Map<String, String> messagetest(@PathVariable String locale){
        String usmsg=webcontext.getMessage("info", null, Locale.US);
        String komsg=webcontext.getMessage("info", null, Locale.KOREA);
        String jamsg=webcontext.getMessage("info", null, Locale.JAPAN);

        String myusMsg=webcontext.getMessage("myinfo", new Object[]{"bsyoo",19},Locale.US);
        String mykrMsg=webcontext.getMessage("myinfo", new Object[]{"bsyoo",19},Locale.KOREA);
        String myjaMsg=webcontext.getMessage("myinfo", new Object[]{"bsyoo",19},Locale.JAPAN);

        Map<String,String> map=new HashMap<String,String>();
        map.put("usmsg",usmsg);
        map.put("komsg",komsg);
        map.put("jamsg",jamsg);
        map.put("myusMsg",myusMsg);
        map.put("mykrMsg",mykrMsg);
        map.put("myjaMsg",myjaMsg);
        return map;
    }

    //이셉션을 핸들링을 할 수 있는 예외처리 기능.
    @RequestMapping("/errorhandler")
    public String errorhandler(){

        if(1==1) {
            throw new RuntimeException("errorhandler");
        }

        return "redirect:/";
    }


    @RequestMapping("/myexception")
    public String myexception(){
        throw new MyException("나의 에러를 처리해봐.");
    }



}
