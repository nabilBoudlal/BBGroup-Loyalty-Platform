package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.CostumerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Costumer")
public class SimpleCostumerController implements CostumerController{
    @Autowired
    private CostumerManager costumerManager;
    @Autowired
    private CostumerRepository costumerRepository;

    @Override
    @GetMapping("/{id}")
    public Costumer getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return costumerManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public Costumer create(@RequestBody Costumer object) throws EntityNotFoundException, IdConflictException {
        return costumerManager.create(object);
    }

    @Override
    public Costumer update(Costumer object) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
