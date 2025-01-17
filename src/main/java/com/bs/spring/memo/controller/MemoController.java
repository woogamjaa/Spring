package com.bs.spring.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/memo")
@SessionAttributes({"loginMember"})
public class MemoController {

}
