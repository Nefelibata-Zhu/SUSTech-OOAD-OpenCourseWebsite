package com.example.openCourse.dto;

import java.time.LocalDateTime;

public class UserRequest {
    private long userId;
    private String userName;
    private String userSign;
    private LocalDateTime birthday;
    private String email;
    private String password;
    private String userKind;


    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getUserId(){
        return this.userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }
}

