package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.FidelityCardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/FidelityCard")
public class SimpleFidelityCardController implements FidelityCardController{

    @Autowired
    private FidelityCardManager fidelityCardManager;

    @GetMapping("/{id}")
    @Override
    public FidelityCard getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return fidelityCardManager.getInstance(id);
    }

    @PostMapping("/createNew")
    @Override
    public FidelityCard create(@RequestBody FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return fidelityCardManager.create(object);
    }

    @Override
    public FidelityCard update(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return fidelityCardManager.delete(id);
    }

    @GetMapping("/exists/{id}")
    @Override
    public boolean exists(@PathVariable Long id) {
        return fidelityCardManager.exists(id);
    }

    @Override
    public void addNewCardToCostumer(Costumer costumer) throws IdConflictException, EntityNotFoundException {
        FidelityCard card= new FidelityCard(costumer);
        costumer.addCard(this.create(card));
    }
}
