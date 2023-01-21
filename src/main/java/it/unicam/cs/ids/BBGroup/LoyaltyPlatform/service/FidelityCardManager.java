package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;

public interface FidelityCardManager extends EntityManager<FidelityCard, Long>{
    int updatePoints(FidelityCard object, int points) throws EntityNotFoundException, IdConflictException;
}
