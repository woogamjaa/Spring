package com.bs.spring.ajax.controller;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class RestMemberConteroller {
    private final MemberService memberService;
    @GetMapping
    public List<Member> getMembers() {
        return null;
    }

    @PostMapping
    public int createMember(@RequestBody Member member) {
        return 0;
    }

    @PutMapping
    public int updateMember(@RequestBody Member member) {
        return 0;
    }

    @GetMapping("/{name}")
    public Member getMember(@PathVariable String name) {
        return null;
    }
}
