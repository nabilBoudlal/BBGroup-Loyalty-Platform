package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimpleCostumerManager implements CostumerManager{
    @Autowired
    private CostumerRepository costumerRepository;
    @Override
    public Costumer getInstance(Long id) throws EntityNotFoundException {
        return costumerRepository.findById(id).orElseThrow(() ->
                 new EntityNotFoundException("Cliente con id "+id+"non trovata"));
    }

    @Override
    public Costumer create(Costumer object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesNotNull(object);
        checkCreation(object);
        return costumerRepository.save(object);
    }

    @Override
    public Costumer update(Costumer object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public Costumer updateWithLoyaltyProgram(Long costumerId, LoyaltyProgram program) throws EntityNotFoundException, IdConflictException {
        Costumer costumer= costumerRepository.findById(costumerId).orElseThrow();
        costumer.getFidelityCard().addLoyaltyProgram(program);
        return  costumerRepository.save(costumer);
    }
    @Override
    public boolean delete(Long id) throws IdConflictException, EntityNotFoundException {
        if(!costumerRepository.existsById(id)) throw new EntityNotFoundException("Consumatore con id: "+id+" non trovato");
        costumerRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return costumerRepository.existsById(id);
    }

    private void checkCreation(Costumer costumer) throws EntityNotFoundException, IdConflictException{
        if(costumerRepository.existsByEmail(costumer.getEmail())) throw new IdConflictException("Cliente già esistente");
    }

    private void checkIfValuesNotNull(Costumer costumer) throws NullPointerException {
        Objects.requireNonNull(costumer.getEmail(), "Email non inserita");
        Objects.requireNonNull(costumer.getPhone(), "Telefono non inserito");
    }
}
