package com.bs.spring.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @RequestMapping("/basicAjax")
    public String basicAjax() {
        return "ajax/basicAjax";
    }

    @RequestMapping("/dataAjax")
    @ResponseBody
    public List<String> dataAjax() {
        List<String> names=List.of("유병승","김통통","우감자","오반장","최선생");
        return names;
    }
}
