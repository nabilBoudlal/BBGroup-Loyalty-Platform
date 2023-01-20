package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.LoyaltyProgramManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/LoyaltyProgram")
public class SimpleLoyaltyProgramController implements LoyaltyProgramController{
    @Autowired
    private LoyaltyProgramManager loyaltyProgramManager;


    @GetMapping("/{id}")
    @Override
    public LoyaltyProgram getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return loyaltyProgramManager.getInstance(id);
    }

    @PostMapping("/createNew")
    @Override
    public LoyaltyProgram create(@RequestBody LoyaltyProgram object) throws EntityNotFoundException, IdConflictException {
        return loyaltyProgramManager.create(object);
    }

    @Override
    public LoyaltyProgram update(LoyaltyProgram object) throws EntityNotFoundException, IdConflictException {
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
}
