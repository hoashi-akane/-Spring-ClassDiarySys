package com.madder.diary1801182.Repository;

import com.madder.diary1801182.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {

    @Query("SELECT CASE WHEN count(Student) > 0 THEN true ELSE false END FROM Student Where studentId = :studentId AND studentPassword = :studentPassword")
    boolean accountExists(@Param("studentId") String studentId, @Param("studentPassword") String studentPassword);

    @Query("FROM Student WHERE studentId = :studentId AND studentPassword = :studentPassword")
    Student getUserInfo(@Param("studentId") String studentId, @Param("studentPassword") String studentPassword);
}