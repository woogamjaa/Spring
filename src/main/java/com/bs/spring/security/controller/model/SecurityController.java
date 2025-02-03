package com.bs.spring.security.controller.model;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SecurityController {
    @RequestMapping("/loginsuccess")
    public String loginend() {
        //로그인한 사용자 정보 확인하기
        Object loginMember=SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        log.debug("{}",loginMember);

        log.debug("로그인 성공 !!");
        return "redirect:/";
    }

    @RequestMapping("/loginfail")
    public String loginfail(Model model) {
        log.debug("로그인 실패");
        model.addAttribute("msg","로그인실패");
        model.addAttribute("loc","/login");
        return "common/msg";

    }

    @RequestMapping("/loginpage")
    public String loginpage(Model model) {
        return "common/login";

    }
}
