package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;

public interface LoyaltyProgramManager extends EntityManager<LoyaltyProgram,Long> {
    Activity enrollActivityToLoyaltyProgram(String programName, String adminEmail);
}
