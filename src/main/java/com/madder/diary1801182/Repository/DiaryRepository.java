package com.madder.diary1801182.Repository;

import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Entity.Diary;
import com.madder.diary1801182.Entity.DiaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, DiaryId>{

    @Query("SELECT Diary FROM Diary WHERE setClassCode = :classCode")
    List<Diary> getDiaryList(@Param("diaryId")  String classCode);

}