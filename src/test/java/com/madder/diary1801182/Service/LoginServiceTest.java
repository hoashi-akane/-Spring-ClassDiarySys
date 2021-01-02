package com.madder.diary1801182.Service;

import com.madder.diary1801182.Entity.Class;
import com.madder.diary1801182.Entity.Course;
import com.madder.diary1801182.Entity.Student;
import com.madder.diary1801182.Form.LoginForm;
import com.madder.diary1801182.Repository.ClassRepository;
import com.madder.diary1801182.Repository.CourseRepository;
import com.madder.diary1801182.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("LoginServiceのテスト")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoginServiceTest {

    @MockBean
    StudentRepository studentRepository;
    @MockBean
    ClassRepository classRepository;
    @MockBean
    CourseRepository courseRepository;

    @SpyBean
    LoginService loginService;

    LoginForm loginForm;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void 前準備(){
        passwordEncoder = new BCryptPasswordEncoder();
        loginForm = new LoginForm();
        loginForm.setUserId("1");
        loginForm.setPassword("test");

        String encodedPassword = passwordEncoder.encode(loginForm.getPassword());
        when(studentRepository.getUserPass(any())).thenReturn(encodedPassword);
        when(studentRepository.getUserInfo(any(),any())).thenReturn(new Student());
        when(classRepository.getUserClass(any())).thenReturn(new Class());
        when(courseRepository.getOne(any())).thenReturn(new Course());
    }

    @Test
    void パスワード一致(){

        assertNotNull(loginService.authentication(loginForm));
    }

    @Test
    void パスワード不一致(){

//      入力されたパスワードを変える
        loginForm.setPassword("badPassword");
        assertNull(loginService.authentication(loginForm));
    }


}