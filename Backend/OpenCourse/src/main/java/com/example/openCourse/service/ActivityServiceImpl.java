package com.example.openCourse.service;

import com.example.openCourse.api.ActivityRepository;
import com.example.openCourse.domain.ActivitiesToBeDone;
import com.example.openCourse.domain.StudentClassInfo;
import com.example.openCourse.domain.UserR;
import com.example.openCourse.domain.userKinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository){this.activityRepository = activityRepository;}


    @Override
    public List<ActivitiesToBeDone> findAllActivity() {
        return activityRepository.findAll();
    }

    @Override
    public List<ActivitiesToBeDone> findAllActivityByApplicantId(long applicantID) {
        return activityRepository.findActivitiesToBeDoneByApplicantId(applicantID);
    }

    @Override
    public List<ActivitiesToBeDone> findAllActivityByActivityOwnerId(long activityOwnerId) {
        return activityRepository.findActivitiesToBeDoneByActivityOwnerId(activityOwnerId);
    }


    @Override
    public ActivitiesToBeDone addActivitiesToBeDone(userKinds activityKind, long activityOwnerId, long applicantId, long courseId, LocalDateTime applyTime) {
        return activityRepository.save(new ActivitiesToBeDone(activityKind, activityOwnerId, applicantId, courseId, applyTime));
    }

    @Override
    public void deleteActivityByActivityId(long activityId) {
        activityRepository.deleteActivitiesToBeDoneByActivityId(activityId);
    }

    @Override
    public StudentClassInfo handleActivity(long activityId, boolean isAccept) {
        ActivitiesToBeDone activityToBeHandle = activityRepository.findActivitiesToBeDoneByActivityId(activityId);
        StudentClassInfo result = null;
        if(isAccept){
            activityToBeHandle.setIsAccept(1);
            if(activityToBeHandle.getActivityKind() == userKinds.STUDENT){
                result = new StudentClassInfo(activityToBeHandle.getApplicantId(), activityToBeHandle.getCourseId());
            }else if(activityToBeHandle.getActivityKind() == userKinds.TEACHER){
                result = new StudentClassInfo(activityToBeHandle.getActivityOwnerId(), activityToBeHandle.getCourseId());
            }
        }else{
            activityToBeHandle.setIsAccept(-1);
        }
        activityRepository.save(activityToBeHandle);
        return result;
    }
}
