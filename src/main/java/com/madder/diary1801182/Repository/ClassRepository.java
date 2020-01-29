package com.madder.diary1801182.Repository;

import com.madder.diary1801182.Entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, String>, JpaSpecificationExecutor<Class> {

    @Query("SELECT c FROM Class c WHERE c.classCode = :classCode")
    Class getUserClass(@Param("classCode")String classCode);

}