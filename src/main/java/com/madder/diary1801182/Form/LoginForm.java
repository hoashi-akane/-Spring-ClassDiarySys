package com.madder.diary1801182.Form;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class LoginForm implements Serializable {

    @NotNull
    @Size(min = 1,max = 127)
    private String userId;

    @NotNull
    @Size(min = 1,max = 127)
    private String password;
}
