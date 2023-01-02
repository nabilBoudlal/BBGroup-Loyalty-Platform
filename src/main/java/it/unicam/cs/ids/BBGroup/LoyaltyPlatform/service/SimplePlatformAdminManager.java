package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;


import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.PlatformAdmin;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.PlatformAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimplePlatformAdminManager implements PlatformAdminManager{

    @Autowired
    private PlatformAdminRepository adminRepository;

    @Override
    public PlatformAdmin getInstance(Long id) throws EntityNotFoundException {
        return adminRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Admin con id: "+id+" non trovato"));
    }

    @Override
    public PlatformAdmin create(PlatformAdmin object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesAreNotNull(object);
        checkCreation(object);
        return adminRepository.save(object);
    }

    @Override
    public PlatformAdmin update(PlatformAdmin object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        if(!this.exists(id)) throw new EntityNotFoundException("Admin non presente");
        adminRepository.deleteById(id);
        return !adminRepository.existsById(id);
    }

    @Override
    public boolean exists(Long id) {
        return adminRepository.existsById(id);
    }

    private void checkCreation(PlatformAdmin admin) throws EntityNotFoundException, IdConflictException{
        if(adminRepository.existsByEmail(admin.getEmail()))throw  new IdConflictException("Admin già presente");
    }

    private void checkIfValuesAreNotNull(PlatformAdmin admin) throws NullPointerException{
        Objects.requireNonNull(admin.getEmail(), "Email non inserita");
        Objects.requireNonNull(admin.getPhone(), "Telefono non inserito");
    }
}
