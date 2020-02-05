package com.madder.diary1801182.Service;

import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Entity.Diary;
import com.madder.diary1801182.Repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    public List<DiaryDto> getDiaryList(String classCode){

        List<DiaryDto> diaryDtoList = null;

        List<Diary> diaryList = diaryRepository.getDiaryList(classCode);

        // Entity -> Dto
        if(diaryList != null){

            diaryDtoList = new ArrayList<DiaryDto>();

            for(Diary diary : diaryList){
                DiaryDto diaryDto = new DiaryDto();

                diaryDto.setClassCode(diary.getClassCode());
                diaryDto.setStudentId(diary.getStudentId());
                diaryDto.setInsertDate(diary.getInsertDate());
                diaryDto.setGoodPoint(diary.getGoodPoint());
                diaryDto.setBadPoint(diary.getBadPoint());
                diaryDto.setStdCom(diary.getStudentComment());
                diaryDto.setTcrCom(diary.getTeacherComment());

                diaryDtoList.add(diaryDto);
            }
        }
        return diaryDtoList;
    }
}
