package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.PersonalizedCashbackRule;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.SimpleRuleLevel;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.SimpleRulePoint;
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

    @Override
    public Rule create(Rule object) throws EntityNotFoundException, IdConflictException {
        return ruleManager.create(object);
    }

    @PostMapping("/createNewRulePoint")
    public Rule createNewRulePoint() throws EntityNotFoundException, IdConflictException {
        return ruleManager.create(new SimpleRulePoint());
    }
    @PostMapping("/createNewLevelRule")
    public Rule createNewLevelRule() throws EntityNotFoundException, IdConflictException {
        return ruleManager.create(new SimpleRuleLevel());
    }

    @PostMapping("/createNewPersonalizedRule/{type}/{value}")
    public Rule createNewPersonalizedRule(@PathVariable String type,@PathVariable int value) throws IdConflictException, EntityNotFoundException {
        if(!type.equals("cashback"))throw  new IllegalArgumentException("Tipologia non valida");
        return ruleManager.create(new PersonalizedCashbackRule(value));
    }

    @Override
    public Rule update(Rule object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return ruleManager.delete(id);
    }

    @GetMapping("/exists/{id}")
    @Override
    public boolean exists(@PathVariable Long id) {
        return ruleManager.exists(id);
    }


}
