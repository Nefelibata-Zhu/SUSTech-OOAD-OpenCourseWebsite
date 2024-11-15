package com.example.openCourse.service;

import com.example.openCourse.api.UserRepository;
import com.example.openCourse.domain.UserR;
import com.example.openCourse.domain.userKinds;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserR> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserR findByUserId(long userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public UserR findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional
    public UserR addNewUser(String userName, String userSign, LocalDateTime birthday, String email, String password, userKinds userKind) {
        //TODO: 密码加密
        return userRepository.save(new UserR(userName,userSign,birthday,email,password,userKind));
    }

    @Override
    @Transactional
    public void deleteUserByUserId(long userId) {
        userRepository.deleteUserByUserId(userId);
    }

    @Override
    @Transactional
    public void updateUserName(UserR userToUpdate, String userName){
        userToUpdate.setUserName(userName);
        userRepository.save(userToUpdate);
    }

    @Override
    public void updateUserSign(UserR userToUpdate, String userSign) {
        userToUpdate.setUserSign(userSign);
        userRepository.save(userToUpdate);
    }

    @Override
    public void updateUserBirthday(UserR userToUpdate, LocalDateTime birthday) {
        userToUpdate.setBirthday(birthday);
        userRepository.save(userToUpdate);
    }

    @Override
    public void updateUserEmail(UserR userToUpdate, String email) {
        userToUpdate.setEmail(email);
        userRepository.save(userToUpdate);
    }


    @Override
    public UserR userLogin(long userId, String email, String password) {
        //userId默认-1，如果为-1代表用email来登录，否则优先用userId登录
        UserR userToLog;
        if (userId != -1){
            userToLog = userRepository.findUserByUserId(userId);
        }else{
            userToLog = userRepository.findUserByEmail(email);
        }
        if(userToLog == null){
            throw new IllegalArgumentException("Couldn't find the user");
        }
        if(userToLog.checkPassword(password)){
            return userToLog;
        }
        return null;
    }
}
