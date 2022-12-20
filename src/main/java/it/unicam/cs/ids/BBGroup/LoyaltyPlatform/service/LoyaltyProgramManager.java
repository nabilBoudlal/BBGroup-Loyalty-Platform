package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyRule;

import java.util.Collection;

public interface LoyaltyProgramManager extends EntityManager<LoyaltyProgram,Long> {
    Activity enrollActivityToLoyaltyProgram(String programName, String adminEmail);

    Collection<Activity> showAllEnrolledActivities(String programName);

    Collection<FidelityCard> showAllEnrolledCard(String programName);

    Collection<LoyaltyRule> showActiveRules(String programName);
}
