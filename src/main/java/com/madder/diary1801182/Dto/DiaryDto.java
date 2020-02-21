package com.madder.diary1801182.Dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder

public class DiaryDto implements Serializable {

    @NotNull
    private String insertDate;
    @NotNull
    private String classCode;
    @NotNull
    private String studentId;
    @NotNull
    private String goodPoint;
    @NotNull
    private String badPoint;
    @NotNull
    private String stdCom;
    @NotNull
    private String tcrCom;

    @Tolerate
    DiaryDto(){

    }
}
