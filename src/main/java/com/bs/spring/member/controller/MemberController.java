package com.bs.spring.member.controller;


import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

//@RequiredArgsConstructor
//final로 선언된 필드를 매개변수로 선언하는 생성자.

//@AllArgsConstructor
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"}) // SessionScope 를 가진다.
public class MemberController {

    private final BCryptPasswordEncoder passwordEncoder;
    private MemberService service;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public MemberController(MemberService service, BCryptPasswordEncoder encoder, BCryptPasswordEncoder passwordEncoder) {
        this.service = service;
        this.encoder = encoder;
        this.passwordEncoder = passwordEncoder;
    }

    //  private DemoController controller=new DemoController();  << @AllArgsConstructor 이것과 쓸 수 없다.


    @RequestMapping("/login.do")
    public String login(String userId,String pw, Model model, HttpSession session) {
        Member member=service.selectMemberById(userId);
        //passwordEncoder.encode(pw);
        //원본값과 암호화값을 비교하는 메소드를 제공
        //passwordEncoder.matches(pw, member.getPassword()); // 원본값. 암호화값.

        //if(member==null || !member.getPassword().equals(pw)) {
        if(member==null || passwordEncoder.matches(pw, member.getPassword())) {
            //로그인 실패
            model.addAttribute("msg", "아이디와 패스워드가 일치하지 않습니다.");
            model.addAttribute("loc", "/");
            //msg 페이지로.
            return "common/msg";
        }else {
            //로그인 성공
            //session.setAttribute("loginMember", member);
            model.addAttribute("loginMember",member);  //생명주기 관련해서 안댐. 위에  @SessionAttributes({"loginMember"}) 이걸 넣으면 가능.
            //메인으로
            return "redirect:/";
        }
    }

    //로그아웃.
    @RequestMapping("/logout.do")
    public String logout(SessionStatus status) {
        if(!status.isComplete()){
            status.setComplete(); // session 삭제하는 메소드
        }
        return "redirect:/";
    }




//    @GetMapping("/enrollmember.do")
//    public String enrollMemberPage() {
//        return "member/enrollMember";
//    }

    // /member/enrollmember.do -> /WEB-INF/views/member/enrollmember.jsp
    // 리턴값 생략하기.
//    @GetMapping("/enrollmember.do")
//    public void enrollmember(){
//
//    }

    @PostMapping("/enrollmemberend.do")
    public String enrollMemberEnd(Member inputMember, Model model) {
        //BCryptPasswordEncoder를 이용해서 비밀번호 암호화하기
        String encPw=encoder.encode(inputMember.getPassword());
        System.out.println(encPw);
        inputMember.setPassword(encPw);
        int result=service.saveMember(inputMember);

        String msg,loc,viewName="common/msg";
        if(result>0) {
           // msg="회원가입성공";
           // loc="/";

            //return "redirect:/";
            viewName="redirect:/";
        }else{
            msg="회원가입실패";
            loc="/member/enrollmember.do";
            model.addAttribute("msg",msg);
            model.addAttribute("loc",loc);

        }
        return viewName;
    }

}
