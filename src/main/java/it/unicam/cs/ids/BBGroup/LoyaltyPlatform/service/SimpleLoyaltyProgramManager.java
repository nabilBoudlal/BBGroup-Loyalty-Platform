package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleLoyaltyProgramManager implements LoyaltyProgramManager{
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public LoyaltyProgram getInstance(Long id) throws EntityNotFoundException {
        return loyaltyProgramRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Non esiste un programma fedeltà con questo identificativo "+id));
    }

    @Override
    public LoyaltyProgram create(LoyaltyProgram object) throws EntityNotFoundException, IdConflictException {
        return loyaltyProgramRepository.save(object);
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
}
