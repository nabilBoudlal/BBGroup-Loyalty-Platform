package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.RuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Rule")
public class SimpleRuleController implements RuleController{
    @Autowired
    private RuleManager ruleManager;
    @GetMapping("/{id}")
    @Override
    public Rule getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return ruleManager.getInstance(id);
    }

    @PostMapping("/createNew")
    @Override
    public Rule create(@RequestBody Rule object) throws EntityNotFoundException, IdConflictException {
        return ruleManager.create(object);
    }

    @Override
    public Rule update(Rule object) throws EntityNotFoundException, IdConflictException {
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
