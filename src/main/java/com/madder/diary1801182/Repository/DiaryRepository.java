package com.madder.diary1801182.Repository;

import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Entity.Diary;
import lombok.NoArgsConstructor;
import org.hibernate.event.spi.DeleteEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Diary>{

//    日誌一覧取得
    @Query("FROM Diary WHERE classCode = :classCode ORDER BY insertDate")
    public List<Diary> getDiaryList(@Param("classCode")  String classCode);

//    修正を行う日誌取得（１件）
    @Query("FROM Diary WHERE insertDate = :insertDate AND classCode = :classCode")
    public Diary getRevisionDiaryList(@Param("insertDate") java.sql.Date insertDate, @Param("classCode") String classCode);

//    日付の日誌がなければtrueを返す
    @Query("SELECT CASE WHEN count(diaryCode) = 0 THEN true ELSE false END FROM Diary WHERE classCode = :classCode AND insertDate = :insertDate")
    public boolean diaryExists(@Param("classCode") String classCode,@Param("insertDate") Date insertDate);

//    日誌の日付のみ全県取得
    @Query(value = "SELECT insert_date FROM diary WHERE insert_date BETWEEN DATE_SUB(CURRENT_DATE(), interval 30 DAY) AND CURDATE() + 0 AND class_code = :classCode ORDER BY insert_date", nativeQuery = true)
    public List<Date> getDateDiaryList(@Param("classCode") String classCode);

//    日誌の削除
    @Transactional
    @Modifying
    @Query("DELETE  FROM Diary WHERE insertDate = :insertDate AND classCode = :classCode")
    public void deleteDiary(@Param("insertDate") java.sql.Date insertDate, @Param("classCode") String classCode);

//    日誌修正
    @Transactional
    @Modifying
    @Query("UPDATE Diary SET goodPoint = :goodPoint, badPoint = :badPoint, studentComment = :stdCom WHERE insertDate = :insertDate AND classCode = :classCode")
    public void update(@Param("goodPoint") String goodPoint, @Param("badPoint") String badPoint, @Param("stdCom") String stdCom, @Param("insertDate") java.sql.Date insertDate, @Param("classCode") String classCode);
}