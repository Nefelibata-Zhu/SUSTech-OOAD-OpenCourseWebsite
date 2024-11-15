package com.example.openCourse.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class UserR {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Setter
    @Getter
    @Column(nullable = false)
    private String userName;

    @Setter
    @Getter
    private String userSign;

    @Setter
    @Getter
    private LocalDateTime birthday;

    @Setter
    @Getter
    @Column(unique=true,nullable = false)
    private String email;

    @Getter
    private userKinds userKind;

    private String password;

    public UserR(){}
    public UserR(String userName, String userSign, LocalDateTime birthday, String email,String password){
        this.userName = userName;
        this.userSign = userSign;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public UserR(String userName, String userSign, LocalDateTime birthday, String email, String password, userKinds userKind){
        this.userName = userName;
        this.userSign = userSign;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.userKind = userKind;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public boolean setPassword(String oldPassword, String newPassword){
        if(this.password.equals(oldPassword)){
            this.password = newPassword;
            return true;
        }
        return false;
    }

}
