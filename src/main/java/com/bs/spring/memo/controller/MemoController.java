package com.bs.spring.memo.controller;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.model.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@Controller
@RequestMapping("/memo")
@SessionAttributes({"loginMember"})
public class MemoController {
    private BCryptPasswordEncoder passwordEncoder;
    private MemoService service;


    @Autowired
    public MemoController(MemoService service ,BCryptPasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/memolist.do")
    public String memolist(Model model) {
        List<Memo> memos=service.selectMemoList();
        model.addAttribute("memos",memos);
        return "memo/memoList";
    }

    @PostMapping("/memolistend.do")
    public String memolistend(Memo memo, Model model) {
        String encPw=passwordEncoder.encode(memo.getPassword());
        System.out.println(encPw);
        memo.setPassword(encPw);

        int result = service.saveMemo(memo);
        String msg,loc,viewName="common/msg";
        if(result>0) {
            viewName="redirect:/";
        } else {
            msg="메모등록실패";
            loc="/memo/memolistend.do";
            model.addAttribute("msg",msg);
            model.addAttribute("loc",loc);
        }
        return viewName;
    }


}
