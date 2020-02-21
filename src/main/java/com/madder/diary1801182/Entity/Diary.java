package com.madder.diary1801182.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Table(name = "diary")
@Entity
@Builder
@Data
public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_code", insertable = false, nullable = false)
    private Integer diaryCode;

    @Column(name = "class_code", nullable = false)
    private String classCode;

    @Column(name = "insert_date", nullable = false)
    private Date insertDate;

    @Column(name = "student_id", nullable =false)
    private String studentId;

    @Builder.Default
    @Column(name = "good_point")
    private String goodPoint = "NULL";

    @Builder.Default
    @Column(name = "bad_point")
    private String badPoint = "NULL";

    @Builder.Default
    @Column(name = "student_comment")
    private String studentComment = "NULL";

    @Builder.Default
    @Column(name = "teacher_comment", nullable = false)
    private String teacherComment = "";

    @OneToMany(mappedBy = "classes")
    List<Class> classes;

    @OneToMany(mappedBy ="students")
    List<Student> students;

    @Tolerate
    Diary(){}
}