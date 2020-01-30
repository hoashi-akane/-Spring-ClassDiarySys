package com.madder.diary1801182.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "diary")
@Entity
@Data
public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DiaryId diaryId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "good_point")
    private String goodPoint = "NULL";

    @Column(name = "bad_point")
    private String badPoint = "NULL";

    @Column(name = "student_comment")
    private String studentComment = "NULL";

    @Column(name = "teacher_comment", nullable = false)
    private String teacherComment;

    public String getClassCode(){
        return diaryId.getClassCode();
    }

    public Date getInsertDate(){
        return diaryId.getInsertDate();
    }

    public String setClassCode(){
        return diaryId.getClassCode();
    }

    public Date setInsertDate(){
        return diaryId.getInsertDate();
    }
}