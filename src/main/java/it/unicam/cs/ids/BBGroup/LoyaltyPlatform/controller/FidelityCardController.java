package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;

public interface FidelityCardController extends EntityController<FidelityCard,Long> {

    public FidelityCard createWithLoyaltyProgram(String costumerEmail, String loyaltyProgramName);

    public FidelityCard createWithoutLoyaltyProgram(String costumerEmail) throws Exception;
}
