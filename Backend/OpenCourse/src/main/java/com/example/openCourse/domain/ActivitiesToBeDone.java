package com.example.openCourse.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class ActivitiesToBeDone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activityId;
    private userKinds activityKind;
    private long activityOwnerId;
    private long applicantId;
    @Column(nullable = false)
    private LocalDateTime applyTime;
    @Column(nullable = false)
    private int isAccept;
    private long courseId;
    public ActivitiesToBeDone() {}
    public ActivitiesToBeDone(userKinds activityKind, long activityOwnerId, long applicantId, long courseId, LocalDateTime applyTime){
        this.activityKind = activityKind;
        this.activityOwnerId = activityOwnerId;
        this.applicantId = applicantId;
        this.courseId = courseId;
        this.applyTime = applyTime;
        this.isAccept = 0;
    }

}
