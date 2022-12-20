package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.FidelityCardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/FidelityCard")
public class SimpleFidelityCardController implements FidelityCardController{

    @Autowired
    private FidelityCardManager cardManager;

    @Override
    @GetMapping("/{id}")
    public FidelityCard getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return cardManager.getInstance(id);
    }

    @Override
    public FidelityCard create(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return cardManager.create(object);
    }


    @Override
    public FidelityCard update(FidelityCard object) throws IdConflictException, EntityNotFoundException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws IdConflictException, EntityNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
    @PostMapping("/enrollCard/{programName}/{cardId}")
    public void enrollCardToLoyaltyProgram(@PathVariable String programName, @PathVariable Long cardId) throws EntityNotFoundException{
        cardManager.enrollCardToLoyaltyProgram(programName,cardId);
    }


}
