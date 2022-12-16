package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityAdmin;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleActivityAdminManager implements ActivityAdminManager{
    @Autowired
    private ActivityAdminRepository activityAdminRepository;
    @Override
    public ActivityAdmin getInstance(Long id) throws EntityNotFoundException {
        return activityAdminRepository.findById(id).orElseThrow(() ->
        new EntityNotFoundException("Non esiste un admin con questo Id "+id));
    }

    @Override
    public ActivityAdmin create(ActivityAdmin object) throws EntityNotFoundException, IdConflictException{
        return activityAdminRepository.save(object);
    }

    @Override
    public ActivityAdmin update(ActivityAdmin object) {
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
    public ActivityAdmin findAdminByEmail(String email) {
        return activityAdminRepository.findByEmail(email);
    }
}
