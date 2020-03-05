package com.madder.diary1801182.Controller;

import com.madder.diary1801182.Dto.DateOnlyDiaryDto;
import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Form.DiaryForm;
import com.madder.diary1801182.Service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class DiaryController {

    private final DiaryService diaryService;


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
    @GetMapping("InputDiaryControl")
    public String inputDiary(DiaryForm diaryForm, HttpSession session){

        LoginInfoDto loginInfoDto = (LoginInfoDto)session.getAttribute("loginInfoDto");
        String path = "";

        //日誌がなければtrue
        if(diaryService.checkDiary(loginInfoDto.getClassCode())){
            path = "redirect:/InputTodayDiary";
        }else{
            path = "redirect:/InputOldDiary";
        }

        return path;
    }

    /*
    * 日付が本日の日誌を登録させる
    * */
    @GetMapping("InputTodayDiary")
    public ModelAndView inputTodayDiary(ModelAndView mav, DiaryForm diaryForm){

        Date date = new Date();
        mav.addObject("date",date);
        mav.addObject("diaryForm", diaryForm);
        mav.setViewName("inputTodayDiary");

        return mav;
    }

    /*
    * 当日及び過去の日誌登録処理
     */
    @PostMapping("InsertTodayDiary")
    public ModelAndView insertTodayDiary(ModelAndView mav, @Validated @ModelAttribute DiaryForm diaryForm, BindingResult result,
                                         RedirectAttributes redirectAttributes,DiaryDto diaryDto,HttpSession session){

        String[] msg;
        String path = "";
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("Errormsg", "適切な値を入力してください");
            path = "redirect:/InputDiaryControl";
        }else{
            diaryService.insertDiary(diaryForm,(LoginInfoDto)session.getAttribute("loginInfoDto"));
            msg = new String[]{"日誌登録完了", "日誌登録が完了しました。"};
            path = "completeDiaryResist";
            mav.addObject("msg",msg);
        }
        mav.setViewName(path);

        return mav;
    }

    /*
    * 当日ではない日誌の登録
    * （記入可能な日誌一覧表示）DatePickerで日付選択、jsで追加可能化確認
    * */
    @GetMapping("InputOldDiary")
    public ModelAndView inputOldDiary(ModelAndView mav, HttpSession session, DateOnlyDiaryDto dateOnlyDiaryDto, DiaryForm diaryForm){

        String classCode = ((LoginInfoDto)session.getAttribute("loginInfoDto")).getClassCode();
        dateOnlyDiaryDto = diaryService.dateOnlyDiaryList(classCode);

        mav.addObject("diaryForm", diaryForm);
        mav.addObject("dateOnlyDiaryDto",dateOnlyDiaryDto);
        mav.setViewName("inputOldDiary");

        return mav;
    }
}