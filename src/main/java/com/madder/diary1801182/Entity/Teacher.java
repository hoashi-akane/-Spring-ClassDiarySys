package com.madder.diary1801182.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "teacher")
@Data
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(insertable = false, name = "teacher_code", nullable = false)
    private String teacherCode;

    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "teacher_password", nullable = false)
    private String teacherPassword;

    
}