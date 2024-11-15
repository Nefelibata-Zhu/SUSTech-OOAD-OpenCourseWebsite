package com.example.openCourse.api;

import com.example.openCourse.domain.ActivitiesToBeDone;
import com.example.openCourse.domain.StudentClassInfo;
import com.example.openCourse.domain.userKinds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivitiesToBeDone, Long>{
    public ActivitiesToBeDone findActivitiesToBeDoneByActivityId(long activityId);
    public List<ActivitiesToBeDone> findActivitiesToBeDoneByApplicantId(long applicantId);
    public List<ActivitiesToBeDone> findActivitiesToBeDoneByActivityOwnerId(long activityOwnerId);
    public List<ActivitiesToBeDone> findActivitiesToBeDoneByActivityKind(userKinds activityKind);
    public void deleteActivitiesToBeDoneByActivityId(long activityId);
}
