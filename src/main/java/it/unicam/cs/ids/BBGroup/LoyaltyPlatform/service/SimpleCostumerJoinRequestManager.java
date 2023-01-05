package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.CostumerJoinRequest;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerJoinRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimpleCostumerJoinRequestManager implements CostumerJoinRequestManager{

    @Autowired
    private CostumerJoinRequestRepository requestRepository;
    @Override
    public CostumerJoinRequest getInstance(Long id) throws EntityNotFoundException {
        return requestRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Richiesta adesione cliente "+id+"non trovata!"));
    }

    @Override
    public CostumerJoinRequest create(CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesAreNotNull(object);
        checkCreation(object);
        return requestRepository.save(object);
    }

    @Override
    public CostumerJoinRequest update(CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        if(!this.exists(id)) throw new EntityNotFoundException("Richiesta non presente");
        requestRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return requestRepository.existsById(id);
    }

    private void checkCreation(CostumerJoinRequest request) throws EntityNotFoundException, IdConflictException{
        if(requestRepository.existsByCostumerEmail(request.getCostumerEmail())) throw new IdConflictException("Richiesta già presente");
        if(requestRepository.existsByPhone(request.getPhone())) throw new IdConflictException("Richiesta già presente");
    }

    private void checkIfValuesAreNotNull(CostumerJoinRequest request) throws NullPointerException{
       Objects.requireNonNull(request.getCostumerEmail(), "Email non inserita");
       Objects.requireNonNull(request.getPhone(), "Telefono non inserito");
    }
}
