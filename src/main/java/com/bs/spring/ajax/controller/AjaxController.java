package com.bs.spring.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @RequestMapping("/basicAjax")
    public String basicAjax() {

        return "ajax/basicAjax";
    }
}
