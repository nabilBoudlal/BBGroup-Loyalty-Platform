package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleRuleManager implements RuleManager{
    @Autowired
    private RuleRepository ruleRepository;
    @Override
    public Rule getInstance(Long id) throws EntityNotFoundException {
        return ruleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(""+id));
    }

    @Override
    public Rule create(Rule object) throws EntityNotFoundException, IdConflictException {
        return ruleRepository.save(object);
    }

    @Override
    public Rule update(Rule object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        if(!ruleRepository.existsById(id)) throw new EntityNotFoundException("Regola con id: "+id+" non trovata!");
        ruleRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return ruleRepository.existsById(id);
    }
}
