package com.example.openCourse.api;


import com.example.openCourse.domain.UserR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserR, Long> {
    public UserR findUserByUserId(long userId);
    public UserR findUserByEmail(String email);
    public void deleteUserByUserId(long userId);
    public boolean deleteUserByEmail(String email);

}
