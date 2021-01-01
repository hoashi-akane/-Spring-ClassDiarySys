package com.madder.diary1801182.Service;

import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Entity.Class;
import com.madder.diary1801182.Entity.Course;
import com.madder.diary1801182.Entity.Student;
import com.madder.diary1801182.Form.LoginForm;
import com.madder.diary1801182.Repository.ClassRepository;
import com.madder.diary1801182.Repository.CourseRepository;
import com.madder.diary1801182.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {


    private final StudentRepository studentRepository;

    private final ClassRepository classRepository;

    private final CourseRepository courseRepository;


    public LoginInfoDto authentication(LoginForm loginForm){

        LoginInfoDto loginInfoDto = null;

//        Bcrypt使用
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = studentRepository.getUserPass(loginForm.getUserId());

        if(passwordEncoder.matches(loginForm.getPassword(), encodedPassword)){
            //認証成功後、ログイン情報を取得
//            formの値書き換え
            loginForm.setPassword(encodedPassword);
            loginInfoDto = getUserInfo(loginForm);
        }
        return loginInfoDto;

//        //ログイン情報取得前にアカウントが正しいかの確認を行う (Old認証方式)
//        if(studentRepository.accountExists(loginForm.getUserId(),loginForm.getPassword())){
//        //認証成功後、ログイン情報を取得
//            loginInfoDto = new LoginInfoDto();
//            loginInfoDto = getUserInfo(loginForm, loginInfoDto);
//        }else{
//
//        }
//        return loginInfoDto;
    }


    public LoginInfoDto getUserInfo(LoginForm loginForm){

        Student student = studentRepository.getUserInfo(loginForm.getUserId(),loginForm.getPassword());
        Class myClass = classRepository.getUserClass(student.getClassCode());
        Course course = courseRepository.getOne(myClass.getCourseCode());

        // Entity to Dto
        LoginInfoDto loginInfoDto = LoginInfoDto.builder()
                .userId(student.getStudentId())
                .userName(student.getStudentName())
                .classCode(student.getClassCode())
                .className(myClass.getClassName())
                .courseCode(myClass.getCourseCode())
                .courseName(course.getCourseName())
                .build();

        return loginInfoDto;
    }

}
