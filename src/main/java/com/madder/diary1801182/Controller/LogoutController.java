package com.madder.diary1801182.Controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;


@Controller
public class LogoutController {

    @GetMapping("Logout")
    @ResponseStatus(value= HttpStatus.MOVED_PERMANENTLY)
    public String logout(HttpSession session){

        session.invalidate();
        String path="redirect:/Login";

        return path;
    }
}
