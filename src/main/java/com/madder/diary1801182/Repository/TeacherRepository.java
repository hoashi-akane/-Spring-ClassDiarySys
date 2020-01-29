package com.madder.diary1801182.Repository;

import com.madder.diary1801182.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String>, JpaSpecificationExecutor<Teacher> {

}