package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyRule;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleLoyaltyRuleManager implements LoyaltyRuleManager{

    @Autowired
    private LoyaltyRuleRepository ruleRepository;
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public LoyaltyRule getInstance(Long id) throws EntityNotFoundException {
        return ruleRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Regola non trovata"));
    }

    @Override
    public LoyaltyRule create(LoyaltyRule object) throws EntityNotFoundException, IdConflictException {
        return ruleRepository.save(object);
    }

    @Override
    public LoyaltyRule update(LoyaltyRule object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }


}
