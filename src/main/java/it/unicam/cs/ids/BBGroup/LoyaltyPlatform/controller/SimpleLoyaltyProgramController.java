package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.RuleRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.ActivityManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.LoyaltyProgramManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/LoyaltyProgram")
public class SimpleLoyaltyProgramController implements LoyaltyProgramController{
    @Autowired
    private LoyaltyProgramManager loyaltyProgramManager;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ActivityManager activityManager;


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

    @PostMapping("/createProgramWithRule/{programName}/{ruleName}")
    public LoyaltyProgram createLoyaltyProgramWithRule(@PathVariable String programName,@PathVariable String ruleName) throws IdConflictException, EntityNotFoundException {
        LoyaltyProgram newProgram= (new LoyaltyProgram(programName, ruleRepository.findByRuleName(ruleName)));
        return loyaltyProgramManager.create(newProgram);
    }

    @PostMapping("/enrollActivity/{programName}/{activityEmail}")
    public Activity enrollActivity(@PathVariable String programName, @PathVariable String activityEmail) throws IdConflictException, EntityNotFoundException {
        loyaltyProgramRepository.findByProgramName(programName).enrollActivity(activityRepository.findByEmail(activityEmail));
        activityManager.updateWithLoyaltyProgram(activityRepository.findByEmail(activityEmail).getUserId(),loyaltyProgramRepository.findByProgramName(programName));
        return activityRepository.findByEmail(activityEmail);
    }





}
