package com.madder.diary1801182.Repository;

import com.madder.diary1801182.Entity.Diary;
import com.madder.diary1801182.Entity.DiaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, DiaryId>, JpaSpecificationExecutor<Diary> {

}