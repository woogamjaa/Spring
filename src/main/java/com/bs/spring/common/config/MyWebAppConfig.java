package com.bs.spring.common.config;

import com.bs.spring.common.error.MyException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
public class MyWebAppConfig implements WebMvcConfigurer {
    private static final Log log = LogFactory.getLog(MyWebAppConfig.class);
    //웹이 돌아가는 mvc에 대한 환경설정을 할 수 있는 클래스


    //jsp 화면으로 전환해주는 기능.
    //컨트롤러에 매소드라 url 하지 않아도 일괄적으로 막 등록해서 사용할 수 있다.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
     registry.addViewController("/member/enrollmember.do").setViewName("member/enrollmember");
     registry.addViewController("/memo/memolist.do").setViewName("memo/memoList");

//     registry.addViewController("/board/boarddetail.do").setViewName("board/boardDetail");
//     registry.addViewController("/board/boardlist.do").setViewName("board/boardList");
     registry.addViewController("/boards/boardwrite.do").setViewName("board/boardWrite");

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //국제화 처리 bean 등록하기.
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    //ExceptionHandler를 등록하기
//    @Bean
//    public HandlerExceptionResolver exceptionHandler() {
//
//        SimpleMappingExceptionResolver exceptionHandler = new SimpleMappingExceptionResolver();
//        Properties prop = new Properties();
//        //key:Exception명, value : 연결할 주소.
//        prop.put(RuntimeException.class.getName(), "common/error/runtimeerror");
//
//        //MyException LocaleData
//        prop.put(MyException.class.getName(), "common/error/myerror");
//
//        exceptionHandler.setExceptionMappings(prop);
//
//        //기본 에러처리페이지를 등록.
//        exceptionHandler.setDefaultErrorView("common/errorr/default");
//
//
//        return exceptionHandler;
//    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(1024*1024*100);
//        return multipartResolver;
//    }

    //crossorigin허용하기


    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/api/**")
               .allowedOrigins("http://localhost:52330");
    }
}
