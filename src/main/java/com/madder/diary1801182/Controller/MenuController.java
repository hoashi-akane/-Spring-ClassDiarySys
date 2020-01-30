package com.madder.diary1801182.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MenuController {


    @GetMapping("Menu")
    public String menu(){
        return "menu.html";
    }
}
