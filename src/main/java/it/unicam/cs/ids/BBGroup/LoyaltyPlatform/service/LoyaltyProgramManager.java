package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;

public interface LoyaltyProgramManager extends EntityManager<LoyaltyProgram, Long> {
    LoyaltyProgram updateWithRule(String programName, String ruleName);
}
