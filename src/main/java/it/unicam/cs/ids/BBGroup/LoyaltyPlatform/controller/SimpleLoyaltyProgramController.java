package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.LoyaltyProgramManager;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/LoyaltyProgram")
public class SimpleLoyaltyProgramController implements LoyaltyProgramController{

    @Autowired
    private LoyaltyProgramManager programManager;

    @GetMapping("/{id}")
    @Override
    public LoyaltyProgram getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return programManager.getInstance(id);
    }

    @PostMapping("/createNew")
    @Override
    public LoyaltyProgram create(@RequestBody LoyaltyProgram object) throws EntityNotFoundException, IdConflictException {
        return programManager.create(object);
    }

    @Override
    public LoyaltyProgram update(LoyaltyProgram object) throws IdConflictException, EntityNotFoundException {
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

    @PostMapping("/enroll/{programName}/{adminEmail}")
    public Activity enrollActivityToLoyaltyProgram(@PathVariable String programName, @PathVariable String adminEmail){
       return programManager.enrollActivityToLoyaltyProgram(programName,adminEmail);
    }

    @GetMapping("/list/activity/{programName}")
    public Collection<Activity> showAllEnrolledActivities(@PathVariable String programName){
        return programManager.showAllEnrolledActivities(programName);
    }

    @GetMapping("/list/card/{programName}")
    public Collection<FidelityCard> showAllEnrolledCard(@PathVariable String programName){
        return programManager.showAllEnrolledCard(programName);
    }
}
