package com.madder.diary1801182.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "class")
public class Class implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "class_code", insertable = false, nullable = false)
    private String classCode;

    @Column(name = "course_code", nullable = false)
    private String courseCode;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "class_name")
    private String className = "NULL";

    @Column(name = "teacher_code", nullable = false)
    private String teacherCode;


    @ManyToOne
    Class classes;
    
}