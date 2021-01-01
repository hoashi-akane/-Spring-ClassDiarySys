package com.madder.diary1801182.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class LoginInfoDto implements Serializable {

    private String userId;
    private String userName;
    private String classCode;
    private String className;
    private String courseCode;
    private String courseName;

}
