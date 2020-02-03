package com.madder.diary1801182.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MenuController {

    @GetMapping("Menu")
    public String menu(){
        return "menu";
    }
}
