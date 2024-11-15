package com.example.openCourse.service;
import com.example.openCourse.domain.ActivitiesToBeDone;
import com.example.openCourse.domain.StudentClassInfo;
import com.example.openCourse.domain.userKinds;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityService {
    public List<ActivitiesToBeDone> findAllActivity();
    public List<ActivitiesToBeDone> findAllActivityByApplicantId(long applicantID);
    public List<ActivitiesToBeDone> findAllActivityByActivityOwnerId(long activityOwnerId);
    public ActivitiesToBeDone addActivitiesToBeDone(userKinds activityKind, long activityOwnerId, long applicantId, long courseId, LocalDateTime applyTime);
    public void deleteActivityByActivityId(long activityId);
    public StudentClassInfo handleActivity(long activityId, boolean isAccept);
}
