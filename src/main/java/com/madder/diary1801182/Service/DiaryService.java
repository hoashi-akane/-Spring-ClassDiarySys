package com.madder.diary1801182.Service;

import com.madder.diary1801182.Dto.DateOnlyDiaryDto;
import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Entity.Diary;
import com.madder.diary1801182.Form.DiaryForm;
import com.madder.diary1801182.Form.LoginForm;
import com.madder.diary1801182.Repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    /*
    * 日誌一覧取得サービス
    * */
    public List<DiaryDto> getDiaryList(String classCode){

        List<DiaryDto> diaryDtoList = null;

        List<Diary> diaryList = diaryRepository.getDiaryList(classCode);

        // Entity -> Dto
        if(diaryList != null){

            diaryDtoList = new ArrayList<DiaryDto>();

            for(Diary diary : diaryList){

                String strDate = dateToString(diary.getInsertDate());
                DiaryDto diaryDto = DiaryDto.builder()
                        .classCode(diary.getClassCode())
                        .studentId(diary.getStudentId())
                        .insertDate(strDate)
                        .goodPoint(diary.getGoodPoint())
                        .badPoint(diary.getBadPoint())
                        .stdCom(diary.getStudentComment())
                        .tcrCom(diary.getTeacherComment())
                        .build();

                diaryDtoList.add(diaryDto);
            }
        }
        return diaryDtoList;
    }

    /*
    * 日誌確認サービス(return boolean)
    * */
    public Boolean checkDiary(String classCode){

        Date date = new Date();
        //当日の日誌が作成されていればtrue
        return diaryRepository.diaryExists(classCode, date);
    }

    /*
    * Date型をStringに整形してformに入れる
    * */
    private String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /*
    * 記入積み日誌の日付一覧を取得する
    * */
    public DateOnlyDiaryDto dateOnlyDiaryList(String classCode){

        DateOnlyDiaryDto dateOnlyDaiaryDto = new DateOnlyDiaryDto();
        List<String> dateList = new ArrayList<String>();

        List<Date> dateDiaryList = diaryRepository.getDateDiaryList(classCode);
        for(Date dateDiary : dateDiaryList){

            String strDateDiary = dateToString(dateDiary);
            dateList.add(strDateDiary);
        }
        dateOnlyDaiaryDto.setInsertDate(dateList);

        return dateOnlyDaiaryDto;
    }

    /*
    *  日誌登録サービス(１件)
    * */
    public Boolean insertDiary(DiaryForm diaryForm,LoginInfoDto loginInfoDto){

        String strDate = null;
        if(diaryForm.getInsertDate() == null) {
            Date date = new Date();
            strDate = dateToString(date);
        }else{
            strDate = diaryForm.getInsertDate();
        }

        DiaryDto diaryDto = formToDto(diaryForm, loginInfoDto, strDate);
        Diary entity = dtoToEntity(diaryDto);
        Diary diary = diaryRepository.save(entity);
        return true;
    }

    /*
    * 日誌削除
    * */
    public Boolean delDiary(String[] insertDates, String classCode){

        boolean check = true;
        try {
            for (String insertDate : insertDates) {
                diaryRepository.deleteDiary(java.sql.Date.valueOf(insertDate), classCode);
            }
        }catch(Exception e) {
            check = false;
        }
        return check;
    }

    // dto -> entity
    private Diary dtoToEntity(DiaryDto diaryDto){

        Diary diary = Diary.builder()
                .studentId(diaryDto.getStudentId())
                .insertDate(java.sql.Date.valueOf(diaryDto.getInsertDate()))
                .classCode(diaryDto.getClassCode())
                .studentId(diaryDto.getStudentId())
                .goodPoint(diaryDto.getGoodPoint())
                .badPoint(diaryDto.getBadPoint())
                .studentComment(diaryDto.getStdCom())
                .teacherComment(diaryDto.getTcrCom())
                .build();
        return diary;
    }

    // form -> dto
    private DiaryDto formToDto(DiaryForm diaryForm, LoginInfoDto loginInfoDto, String strDate){

        DiaryDto diaryDto = DiaryDto.builder()
                .insertDate(strDate)
                .classCode(loginInfoDto.getClassCode())
                .studentId(loginInfoDto.getUserId())
                .goodPoint(diaryForm.getGoodPoint())
                .badPoint(diaryForm.getBadPoint())
                .stdCom(diaryForm.getStdCom())
                .tcrCom("")
                .build();

        return diaryDto;
    }
}
