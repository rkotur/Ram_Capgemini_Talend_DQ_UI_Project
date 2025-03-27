package com.cap_talend.program.repository;

import com.cap_talend.program.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.expiredate > :currentDate")
    User findByEmail (@Param("email") String email, @Param("currentDate") LocalDate currentDate);

    //User findByEmail(String email);
}

