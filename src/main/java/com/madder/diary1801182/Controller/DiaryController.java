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


    /*
    日誌一覧表示
     */
    @GetMapping("DispDiaryList")
    public ModelAndView dispDiaryList(ModelAndView mav,HttpSession session){

        LoginInfoDto loginInfoDto = (LoginInfoDto)session.getAttribute("loginInfoDto");

        List<DiaryDto> diaryDtoList = diaryService.getDiaryList(loginInfoDto.getClassCode());

        if(diaryDtoList != null){
            mav.addObject("diaryList",diaryDtoList);
        }else{
            String msg ="登録されている日誌はありません。";
            mav.addObject("msg",msg);
        }

        mav.setViewName("dispDiaryList");
        return mav;
    }

    /*
    日誌作成用入力view表示
     */
    @GetMapping("CreateDiary")
    public Sting inputDiary(DiaryForm diaryForm){

        return "input"
    }

}