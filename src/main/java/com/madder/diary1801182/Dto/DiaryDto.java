package com.madder.diary1801182.Dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DiaryDto implements Serializable {

    private Date insertDate;
    private String classCode;
    private String studentId;
    private String goodPoint;
    private String badPoint;
    private String stdCom;
    private String tcrCom;

}
