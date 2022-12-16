package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityAdmin;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

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
        checkIfParametersAreNotNull(object);
        checkActivityAdmin(object);
        return activityAdminRepository.save(object);
    }

    @Override
    public ActivityAdmin update(ActivityAdmin object) throws EntityNotFoundException, IdConflictException {
        if(! activityAdminRepository.existsByEmail(object.getEmail())) throw new EntityNotFoundException("Nessun admin con email: "+object.getActivityAdminId()+" trovato");
        return activityAdminRepository.save(object);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if(!this.exists(id)) throw new EntityNotFoundException("Nessun admin con id: "+id+"trovata");
        activityAdminRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return activityAdminRepository.existsByActivityAdminId(id);
    }


    private void checkActivityAdmin(ActivityAdmin admin) throws IdConflictException, EntityNotFoundException{
        if(activityAdminRepository.existsByEmail(admin.getEmail()))
            throw new IdConflictException("Un admin con email: "+admin.getEmail()+" è già presente!");
        if(activityAdminRepository.existsByPhone(admin.getPhone()))
            throw new IdConflictException("Un admin con telefono: "+admin.getPhone()+" è già presente!");
    }

    private void checkIfParametersAreNotNull(ActivityAdmin admin) throws NullPointerException{
        Objects.requireNonNull(admin.getEmail(),"Inserire mail valida!");
        Objects.requireNonNull(admin.getPhone(),"Inserire telefono valido!");
    }
}
