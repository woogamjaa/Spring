package com.bs.spring.member.controller;

import com.bs.spring.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @RequestMapping("/enroll.do")
    public String ernoll() {
        System.out.println("회원가입");
        return "member/enrollMember";
    }
}
