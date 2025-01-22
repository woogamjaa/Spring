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
        Runnable r =()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        r.run();
        return "ajax/basicAjax";
    }

    @RequestMapping("/dataAjax")
    @ResponseBody
    //객체는 기본적으로 안보내 진다.
    public List<String> dataAjax() {
        Runnable r =()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        r.run();
        List<String> names=List.of("유병승","김통통","우감자","오반장","최선생"); //jackson 이용해서 배열로 해줌.

        return names;
    }
}
