package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SimpleFidelityCardManager implements FidelityCardManager{

    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    @Override
    public FidelityCard getInstance(Long id) throws EntityNotFoundException {
        return fidelityCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Non esiste una carta fedeltà con questo identificativo "+id));
    }

    @Override
    public FidelityCard create(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return fidelityCardRepository.save(object);
    }

    @Override
    public FidelityCard update(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
@Override
    public int updatePoints(FidelityCard object, int points) throws EntityNotFoundException, IdConflictException {
        fidelityCardRepository.updateTotalPointsByTotalPoints(object.getTotalPoints(), points);
        return object.getTotalPoints();
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
