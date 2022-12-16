package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleActivityManager implements ActivityManager{
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ActivityAdminManager adminManager;
    @Override
    public Activity getInstance(Long id) throws EntityNotFoundException {
        return activityRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Non c'è un'attività con questo Id"));
    }

    @Override
    public Activity create(Activity object) throws EntityNotFoundException, IdConflictException{
        return activityRepository.save(object);
    }

    @Override
    public Activity update(Activity object) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public Activity createActivityWithAdminEmail(Activity activity) {
        activity.setActivityAdmin(adminManager.findAdminByEmail(activity.getAdminEmail()));
        return activityRepository.save(activity);
    }
}
