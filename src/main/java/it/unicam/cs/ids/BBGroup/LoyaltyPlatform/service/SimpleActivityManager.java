package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimpleActivityManager implements  ActivityManager{

    @Autowired
    private ActivityRepository activityRepository;
    @Override
    public Activity getInstance(Long id) throws EntityNotFoundException {
        return activityRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Attività  con id: "+id+" non trovata!"));
    }

    @Override
    public Activity create(Activity object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesAreNotNull(object);
        checkCreation(object);
        return activityRepository.save(object);
    }

    @Override
    public Activity update(Activity object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
    @Override
    public Activity updateWithLoyaltyProgram(Long activityId, LoyaltyProgram program) throws EntityNotFoundException, IdConflictException {
        Activity activity= activityRepository.findById(activityId).orElseThrow();
        activity.setLoyaltyProgram(program);
        return  activityRepository.save(activity);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    private void checkCreation(Activity activity) throws EntityNotFoundException, IdConflictException{
        if(activityRepository.existsByEmail(activity.getEmail()))throw  new IdConflictException("Attività già presente");
    }

    private void checkIfValuesAreNotNull(Activity activity) throws NullPointerException{
        Objects.requireNonNull(activity.getEmail(), "Email non inserita");
        Objects.requireNonNull(activity.getPhone(), "Telefono non inserito");
    }

}
