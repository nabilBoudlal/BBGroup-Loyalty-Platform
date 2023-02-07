package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller.EntityController;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;

public interface CostumerManager extends EntityController<Costumer, Long> {

    Costumer updateWithLoyaltyProgram(Long costumerId, LoyaltyProgram program) throws EntityNotFoundException, IdConflictException;
}
