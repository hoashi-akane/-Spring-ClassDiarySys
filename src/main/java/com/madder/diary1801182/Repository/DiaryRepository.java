package com.madder.diary1801182.Repository;

import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Diary>{

//    日誌一覧取得
    @Query("FROM Diary WHERE classCode = :classCode")
    public List<Diary> getDiaryList(@Param("classCode")  String classCode);

}