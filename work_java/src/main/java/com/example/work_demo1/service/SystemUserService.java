package com.example.work_demo1.service;

import com.example.work_demo1.Dao.SystemUserRepository;
import com.example.work_demo1.pojo.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemUserService {
    @Autowired
    SystemUserRepository userRepository;

    // Get all users
    public List<SystemUser> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    public SystemUser createUser(SystemUser user) {
        return userRepository.save(user);
    }

    // Login
    public SystemUser Login(String account) {
        SystemUser user = userRepository.findByAccount(account);
        return user;
    }


    // Update user
//    public SystemUser updateUser(Long id, SystemUser userDetails) {
//        Optional<SystemUser> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            SystemUser existingUser = user.get();
//            existingUser.setName(userDetails.getName());
//            return userRepository.save(existingUser);
//        }
//        return null;
//    }

    // Delete user
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }

}
