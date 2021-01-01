package com.madder.diary1801182.Controller;

import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Form.LoginForm;
import com.madder.diary1801182.Service.LoginService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class LoginController {

    @NonNull
    private final LoginService loginService;

    /*
    *ログイン画面表示用コントローラ
    *  */
    @GetMapping("Login")
    public String index(Model model,LoginForm loginForm,@ModelAttribute("ErrorMsg") String errorMsg){
        model.addAttribute("loginForm",loginForm);
        model.addAttribute("errorMsg",errorMsg);
        return "login";
    }

    /*
    * ログイン認証コントローラ
    * */
    @PostMapping("Auth")
    @ResponseStatus(value= HttpStatus.MOVED_PERMANENTLY)
    public String auth(@Validated @ModelAttribute LoginForm loginForm, BindingResult result, RedirectAttributes redirectAttributes,HttpSession session){

        String path="";
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("ErrorMsg","不正な値が入力されました。");
            path = "redirect:/Login";
        }else{
            LoginInfoDto loginInfoDto = loginService.authentication(loginForm);

            if(loginInfoDto != null){
                session.setAttribute("loginInfoDto", loginInfoDto);
                path = "redirect:/Menu";
            }else{
                redirectAttributes.addFlashAttribute("ErrorMsg","ログインに失敗しました。<br/>正しいID、PWを入力してください。");
                path = "redirect:/Login";
            }
        }
        return path;
    }

}
