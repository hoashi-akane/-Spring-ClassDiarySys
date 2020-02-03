package com.madder.diary1801182.Controller;

import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    HttpSession session;

    /*
    日誌一覧表示
     */
    @GetMapping("DispDiaryList")
    public String dispDiaryList(Model model){

        LoginInfoDto loginInfo = (LoginInfoDto)session.getAttribute("LoginInfo");

        List<DiaryDto> diaryDtoList = diaryService.getDiaryList(loginInfo.getClassCode());

        if(diaryDtoList != null){
            model.addAttribute("diaryList","diaryDtoList");
        }
        return "dispDiaryList";
    }

}