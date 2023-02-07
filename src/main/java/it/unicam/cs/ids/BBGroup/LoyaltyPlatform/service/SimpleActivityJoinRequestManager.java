package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityJoinRequest;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityJoinRequestRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimpleActivityJoinRequestManager implements ActivityJoinRequestManager{

    @Autowired
    private ActivityJoinRequestRepository requestRepository;
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public ActivityJoinRequest getInstance(Long id) throws EntityNotFoundException {
        return requestRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Richiesta adesione esercente con id: "+id+" non trovata!"));
    }

    @Override
    public ActivityJoinRequest create(ActivityJoinRequest object) throws EntityNotFoundException, IdConflictException {
        this.checkIfValuesAreNotNull(object);
        this.checkCreation(object);
        return requestRepository.save(object);
    }

    @Override
    public ActivityJoinRequest update(ActivityJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        if(!this.exists(id))throw new EntityNotFoundException("Richiesta non presente");
        requestRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return requestRepository.existsById(id);
    }

    private void checkCreation(ActivityJoinRequest request) throws EntityNotFoundException, IdConflictException{
        if(requestRepository.existsByEmail(request.getActivityEmail()))throw  new IdConflictException("Richiesta già presente");
        if(activityRepository.existsByEmail(request.getActivityEmail()))throw  new IdConflictException("Attività già presente");
    }

    private void checkIfValuesAreNotNull(ActivityJoinRequest request) throws NullPointerException{
        Objects.requireNonNull(request.getActivityEmail(), "Email non inserita");
        Objects.requireNonNull(request.getPhone(), "Telefono non inserito");
    }
}
