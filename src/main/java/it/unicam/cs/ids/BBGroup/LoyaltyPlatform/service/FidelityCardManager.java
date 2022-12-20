package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;

public interface FidelityCardManager extends EntityManager<FidelityCard,Long> {


    void enrollCardToLoyaltyProgram(String programName, Long cardId) throws EntityNotFoundException;
}
