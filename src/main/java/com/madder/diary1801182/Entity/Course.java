package com.madder.diary1801182.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "course")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_code", insertable = false, nullable = false)
    private String courseCode;

    @Column(name = "course_name", nullable = false)
    private String courseName;

}