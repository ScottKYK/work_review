package com.example.work_demo1.Dao;

import com.example.work_demo1.pojo.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser,Long> {
    //  Login
    SystemUser findByAccount(String account);


}
