package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;

public interface ActivityManager extends EntityManager<Activity, Long>{

    Activity updateWithLoyaltyProgram(Long activityId, LoyaltyProgram program) throws EntityNotFoundException, IdConflictException;
}
