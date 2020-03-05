package com.madder.diary1801182.Dto;


import lombok.Data;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class DateOnlyDiaryDto implements Serializable {

    @NotNull
    private List<String> insertDate;


}
