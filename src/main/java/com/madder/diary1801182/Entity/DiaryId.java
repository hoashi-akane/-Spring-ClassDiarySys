package com.madder.diary1801182.Entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Data
public class DiaryId implements Serializable {

    @Embedded

    @Column(name = "class_code", insertable = false, nullable = false)
    private String classCode;

    @Column(name = "insert_date", insertable = false, nullable = false)
    private Date insertDate;
}
