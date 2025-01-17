package com.bs.spring.common.interceptor;

import com.bs.spring.common.ViewTemplate;
import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("basicinter")
public class BasicInterceptror implements HandlerInterceptor {


    @Autowired
    private DemoService demoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("인터셉터실행 : prehandle");
        System.out.println("요청주소 : "+request.getRequestURI());
//      System.out.println(demoService.selectDemoList());

//      HandlerMethod handlerMethod = (HandlerMethod) handler;
//      System.out.println(handlerMethod.getBean().getClass().getName());
//      System.out.println(handlerMethod.getMethod().getName());
//        request.setAttribute("msg", "메롱 못 가지 ! ");
//        request.setAttribute("loc", "/");
//        request.getRequestDispatcher("/WEB-INF/views/"+ ViewTemplate.MESSAGE_VIEW+".jsp").forward(request, response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("인터셉터실행 : postHandle");
        System.out.println(modelAndView);
        modelAndView.addObject("msg2","금요일 마지막시간 화이팅 !");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("인터셉터실행 : afterCompletion");
        System.out.println(ex);
        if(ex!=null) {
//            response.sendRedirect(request.getContextPath());
//            service.insertLog()
        }
    }
}
