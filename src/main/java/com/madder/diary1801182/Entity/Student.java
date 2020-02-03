package com.madder.diary1801182.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(insertable = false, name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "class_code",insertable = false, nullable = false)
    private String classCode;

    @Column(name = "student_name",insertable = false, nullable = false)
    private String studentName;

    @Column(name = "student_password", nullable = false)
    private String studentPassword;

    @Column(name = "salt", nullable = false)
    private String salt;


    @ManyToOne
    Student students;
    
}