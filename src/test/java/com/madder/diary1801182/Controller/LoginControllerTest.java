package com.madder.diary1801182.Controller;

import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
@DisplayName("LoginControllerのテスト")
@ExtendWith(SpringExtension.class)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private LoginService loginService;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void loginへのリクエスト() throws Exception {
        mockMvc.perform(get("/Login"))
                .andExpect(status().isOk());
    }

    @Nested
    class Auth関連 {

//      ServiceのauthenticationメソッドからLoginInfoDtoが帰ってくればログイン成功、nullなら失敗
        @Test
        public void authへのリクエスト_ログイン失敗時にリダイレクトするか() throws Exception {

            when(loginService.authentication(any())).thenReturn(null);
            ResultActions accept = mockMvc.perform(post("/Auth")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("userId", "10")
                    .param("password", "password")
                    .characterEncoding("utf-8")
            );
            accept.andExpect(status().is(301));
            accept.andExpect(redirectedUrl("/Login"));
        }

        @Test
        public void authへのリクエスト_ログイン成功時にリダイレクトするか() throws Exception {

            //      値がからでもログイン成功する
            LoginInfoDto loginInfoDto = LoginInfoDto.builder()
                    .build();
            when(loginService.authentication(any())).thenReturn(loginInfoDto);

            ResultActions accept = mockMvc.perform(post("/Auth")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("userId", "10")
                    .param("password", "password")
                    .characterEncoding("utf-8")
            );
            accept.andExpect(status().is(301));
            accept.andExpect(redirectedUrl("/Menu"));
        }
    }
}
