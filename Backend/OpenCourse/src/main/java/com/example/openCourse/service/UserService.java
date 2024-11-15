package com.example.openCourse.service;

import com.example.openCourse.domain.UserR;
import com.example.openCourse.domain.userKinds;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    public List<UserR> findAllUser();
    public UserR findByUserId(long userId);
    public UserR findByEmail(String email);
    public UserR addNewUser(String userName, String userSign, LocalDateTime birthday, String email, String password, userKinds userKind);
    public void deleteUserByUserId(long userId);
    public void updateUserName(UserR userToUpdate, String userName);
    public void updateUserSign(UserR userToUpdate, String userSign);
    public void updateUserBirthday(UserR userToUpdate, LocalDateTime birthday);
    public void updateUserEmail(UserR userToUpdate, String email);
    public UserR userLogin(long userId, String email, String password);

}
