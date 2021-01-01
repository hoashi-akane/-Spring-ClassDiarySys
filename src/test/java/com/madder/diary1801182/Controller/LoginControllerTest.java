package com.madder.diary1801182.Controller;

import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Form.LoginForm;
import com.madder.diary1801182.Repository.ClassRepository;
import com.madder.diary1801182.Repository.CourseRepository;
import com.madder.diary1801182.Repository.StudentRepository;
import com.madder.diary1801182.Service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void authへのリクエスト() throws Exception {

        LoginInfoDto loginInfoDto = LoginInfoDto.builder()
                .userId("1")
                .userName("")
                .classCode("student.getClassCode()")
                .className("myClass.getClassName()")
                .courseCode("myClass.getCourseCode()")
                .courseName("course.getCourseName()")
                .build();

        when(loginService.authentication(any())).thenReturn(loginInfoDto);
        ResultActions accept = mockMvc.perform(post("/Auth")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userId", "10")
                .param("password", "password")
                .characterEncoding("utf-8")
        );

        accept.andExpect(status().is(301));
    }
}
