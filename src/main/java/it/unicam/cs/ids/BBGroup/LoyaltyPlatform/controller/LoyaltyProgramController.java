package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface LoyaltyProgramController extends EntityController<LoyaltyProgram, Long> {
    @PostMapping("/unEnrollActivity/{programName}/{activityEmail}")
    boolean unEnrollActivity(@PathVariable String programName, @PathVariable String activityEmail) throws IdConflictException, EntityNotFoundException;
}
