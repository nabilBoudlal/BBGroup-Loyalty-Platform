package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityAdmin;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityAdminRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimpleActivityManager implements ActivityManager{
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityAdminManager adminManager;

    @Autowired
    private ActivityAdminRepository activityAdminRepository;

    @Override
    public Activity getInstance(Long id) throws EntityNotFoundException {
        return activityRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Non c'è un'attività con questo Id"));
    }

    @Override
    public Activity create(Activity object) throws EntityNotFoundException, IdConflictException{
        checkIfFieldsAreNotNull(object);
        checkActivity(object);
        object.setActivityAdmin(activityAdminRepository.findByEmail(object.getAdminEmail()));
        return activityRepository.save(object);
    }

    //Bisogna capire perchè non funziona, non salva nulla perchè dice che è già presente oggetto con stessa mail
    //bisogna fare un controllo a mano sui parametri che cambiano davvero e fare update di questi ?
    @Override
    public Activity update(Activity object) throws EntityNotFoundException, IdConflictException {
        if(!activityRepository.existsByEmail(object.getEmail())) throw new EntityNotFoundException("Nessuna attività con nome: "+object.getName()+" trovata");
        return activityRepository.save(object);
    }

    //cancella anche l'admin che ha creato l'activity
    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        if(!this.exists(id)) throw new EntityNotFoundException("Nessuna attività con id: "+id+"trovata");
        this.getInstance(id).getActivityAdmin().setActivity(null); //tolgo il collegamento fra admin e attività
        activityRepository.deleteByActivityId(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return activityRepository.existsByActivityId(id);
    }

    @Override
    public Activity createWithAdmin(Activity object, String adminEmail) throws EntityNotFoundException, IdConflictException{
        //checkIfFieldsAreNotNull(object);
        //checkActivity(object);
        object.setAdminEmail(adminEmail);
        object.setActivityAdmin(activityAdminRepository.findByEmail(object.getAdminEmail()));
        return activityRepository.save(object);
    }



    /**
     * It checks if the activity already exists in the database
     *
     * @param activity the activity to be checked
     */
    private void checkActivity(Activity activity) throws EntityNotFoundException, IdConflictException{
        if(!activityAdminRepository.existsByEmail(activity.getAdminEmail()))
            throw new EntityNotFoundException("Admin con mail: "+activity.getAdminEmail()+" non trovato");
        if(activityRepository.existsByEmail(activity.getEmail()))
            throw new IdConflictException("Esiste già un'attività con email: "+activity.getEmail());
        if(activityRepository.existsByPhone(activity.getPhone()))
            throw new IdConflictException("Esiste già un'attività con telefono: "+activity.getPhone());
        if(activityRepository.existsByVatCode(activity.getVatCode()))
            throw new IdConflictException("Esiste già un'attività con P.Iva: "+activity.getVatCode());
    }

    private void checkIfFieldsAreNotNull(Activity activity) throws NullPointerException{
        Objects.requireNonNull(activity.getEmail(),"Inserire mail valida");
        Objects.requireNonNull(activity.getPhone(),"Inserire telefono valido");
        Objects.requireNonNull(activity.getAdminEmail(),"Inserire Admin valido");
    }
}
