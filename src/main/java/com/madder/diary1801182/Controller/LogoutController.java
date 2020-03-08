package com.madder.diary1801182.Controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class LogoutController {

    @GetMapping("Logout")
    public String logout(HttpSession session){

        session.invalidate();
        String path="redirect:/Login";

        return path;
    }
}
