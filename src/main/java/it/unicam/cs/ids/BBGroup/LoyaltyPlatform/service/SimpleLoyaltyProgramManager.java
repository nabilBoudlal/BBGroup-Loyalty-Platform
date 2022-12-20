package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityAdmin;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityAdminRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;

@Validated
@Service
public class SimpleLoyaltyProgramManager implements LoyaltyProgramManager {

    @Autowired
    private LoyaltyProgramRepository programRepository;
    @Autowired
    private ActivityAdminRepository activityAdminRepository;
    @Autowired
    private ActivityRepository activityRepository;



    @Override
    public LoyaltyProgram getInstance(Long id) throws EntityNotFoundException {
        return programRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("LoyaltyProgram non trovato"));
    }

    @Override
    public LoyaltyProgram create(LoyaltyProgram object) throws EntityNotFoundException, IdConflictException {
        ActivityAdmin admin = activityAdminRepository.findByEmail(object.getCreatorEmail());
        Activity activity= activityRepository.findByAdminEmail(admin.getEmail());
        activity.setLoyaltyProgram(object);
        object.setActivityAdmin(admin);
        return programRepository.save(object);
    }

    @Override
    public LoyaltyProgram update(LoyaltyProgram object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public Activity enrollActivityToLoyaltyProgram(String programName, String adminEmail){
        programRepository.findByProgramName(programName).enrollActivity(activityRepository.findByAdminEmail(adminEmail));
        return activityRepository.findByAdminEmail(adminEmail);
    }

    @Override
    public Collection<Activity> showAllEnrolledActivities(String programName){
       return programRepository.findByProgramName(programName).getActivities();
    }
    @Override
    public Collection<FidelityCard> showAllEnrolledCard(String programName){
        return programRepository.findByProgramName(programName).getFidelityCards();
    }


}
