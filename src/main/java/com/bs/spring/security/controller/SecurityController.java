package com.bs.spring.security.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SecurityController {
    @RequestMapping("/loginsuccess")
    public String loginend() {
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
}
