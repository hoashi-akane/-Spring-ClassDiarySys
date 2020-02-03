package com.madder.diary1801182.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Table(name = "diary")
@Entity
@Data
public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "diary_code", insertable = false, nullable = false)
    private Integer diaryCode;

    @Column(name = "class_code", nullable = false)
    private String classCode;

    @Column(name = "insert_date", nullable = false)
    private Date insertDate;

    @Column(name = "student_id", nullable =false)
    private String studentId;

    @Column(name = "good_point")
    private String goodPoint = "NULL";

    @Column(name = "bad_point")
    private String badPoint = "NULL";

    @Column(name = "student_comment")
    private String studentComment = "NULL";

    @Column(name = "teacher_comment", nullable = false)
    private String teacherComment;

//    @OneToMany(mappedBy = "classes")
//    List<Class> classes;
//
//    @OneToMany(mappedBy ="students")
//    List<Student> students;
}