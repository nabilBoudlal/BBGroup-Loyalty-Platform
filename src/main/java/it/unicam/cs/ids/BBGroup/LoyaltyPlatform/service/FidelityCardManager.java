package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;

public interface FidelityCardManager extends EntityManager<FidelityCard,Long> {

    public FidelityCard createWithLoyaltyProgram(String costumerEmail, String loyaltyProgramName);

    public FidelityCard createWithoutLoyaltyProgram(String costumerEmail) throws Exception;

}
