package com.madder.diary1801182.Form;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class DiaryForm implements Serializable {


    private String insertDate;

    private String classCode;
    private String userId;
    @NotNull
    private String goodPoint;
    @NotNull
    private String badPoint;
    @NotNull
    private String stdCom;

    private String tcrCom;
}
