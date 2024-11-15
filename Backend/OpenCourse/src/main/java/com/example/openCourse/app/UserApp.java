package com.example.openCourse.app;

import com.example.openCourse.domain.UserR;
import com.example.openCourse.domain.userKinds;
import com.example.openCourse.dto.UserRequest;
import com.example.openCourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApp {
    private final UserService userService;

    @Autowired
    public UserApp(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("/record")
    public List<UserR> findAll(){
//        LocalDateTime now = LocalDateTime.now();
//        userService.addNewUser("1","1",now,"1","1",userKinds.STUDENT);

        return userService.findAllUser();
    }

    @GetMapping("/record/userId/{userId}")
    public UserR findUserByUserId(@PathVariable long userId){
        try {
            return userService.findByUserId(userId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/record/email/{email}")
    public UserR findUserByUserEmail(@PathVariable String email){
        try {
            return userService.findByEmail(email);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest){
        if (userService.findByEmail(userRequest.getEmail()) != null){
            return new ResponseEntity<>("User has exist whose email is: " + userRequest.getEmail(), HttpStatus.UNAUTHORIZED);
        }
        userKinds userKind;
        if(userRequest.getUserKind().equals("SUPERUSER")) {
            userKind = userKinds.SUPERUSER;
        }else if(userRequest.getUserKind().equals("TEACHER")){
            userKind = userKinds.TEACHER;
        }else{
            userKind = userKinds.STUDENT;
        }
        UserR userNew = userService.addNewUser(userRequest.getUserName(), userRequest.getUserSign(), userRequest.getBirthday(), userRequest.getEmail(), userRequest.getPassword(), userKind);
        return new ResponseEntity<>("Successfully create user:"+userNew.getUserId(),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserByUserId(@RequestBody UserRequest userRequest){
        System.out.println(userRequest.getUserId());
        UserR userFound = userService.findByUserId(userRequest.getUserId());
        System.out.println(userFound.getUserId());
        System.out.println(userFound.getUserKind());
        if(userFound == null){
            return new ResponseEntity<>("User whose userId is '"+userRequest.getUserId()+"' doesn't exist",HttpStatus.UNAUTHORIZED);
        }else if((userRequest.getUserId() != userFound.getUserId() && !userRequest.getUserKind().equals("SUPERUSER"))){
            return new ResponseEntity<>("You are not a superuser or the user to be deleted",HttpStatus.UNAUTHORIZED);
        }else if(userFound.getUserKind().toString().equals("SUPERUSER")){
            return new ResponseEntity<>("The user to be deleted is a superuser and you are not the owner",HttpStatus.UNAUTHORIZED);
        }
        userService.deleteUserByUserId(userRequest.getUserId());
        return new ResponseEntity<>("Successfully delete "+userRequest.getUserId(),HttpStatus.OK);
    }

    @PutMapping("/updateUserName")
    public ResponseEntity<String> updateUserName(@RequestBody UserRequest userRequest){
        UserR userToUpdate = userService.userLogin(userRequest.getUserId(), userRequest.getEmail(), userRequest.getPassword());
        userService.updateUserName(userToUpdate, userRequest.getUserName());
        return new ResponseEntity<>("Successfully update user:"+userToUpdate.getUserId(),HttpStatus.OK);
    }

    @PutMapping("/updateUserSign")
    public ResponseEntity<String> updateUserSign(@RequestBody UserRequest userRequest){
        UserR userToUpdate = userService.userLogin(userRequest.getUserId(), userRequest.getEmail(), userRequest.getPassword());
        userService.updateUserSign(userToUpdate, userRequest.getUserSign());
        return new ResponseEntity<>("Successfully update user:"+userToUpdate.getUserId(),HttpStatus.OK);
    }

    @PutMapping("/updateUserBirthday")
    public ResponseEntity<String> updateUserBirthday(@RequestBody UserRequest userRequest){
        UserR userToUpdate = userService.userLogin(userRequest.getUserId(), userRequest.getEmail(), userRequest.getPassword());
        userService.updateUserBirthday(userToUpdate, userRequest.getBirthday());
        return new ResponseEntity<>("Successfully update user:"+userToUpdate.getUserId(),HttpStatus.OK);
    }

    @PutMapping("/updateUserEmail")
    public ResponseEntity<String> updateUserEmail(@RequestBody UserRequest userRequest){
        UserR userToUpdate = userService.userLogin(userRequest.getUserId(), userRequest.getEmail(), userRequest.getPassword());
        userService.updateUserEmail(userToUpdate, userRequest.getEmail());
        return new ResponseEntity<>("Successfully update user:"+userToUpdate.getUserId(),HttpStatus.OK);
    }
}
