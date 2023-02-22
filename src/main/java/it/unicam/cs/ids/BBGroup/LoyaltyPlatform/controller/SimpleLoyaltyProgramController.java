package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.*;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.*;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.ActivityManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.CostumerManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.LoyaltyProgramManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

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
    @Autowired
    private CostumerManager costumerManager;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private FidelityCardRepository fidelityCardRepository;


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
    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return loyaltyProgramManager.delete(id);
    }

    @GetMapping("/exists/{id}")
    @Override
    public boolean exists(@PathVariable Long id) {
        return loyaltyProgramManager.exists(id);
    }

    @PostMapping("/createProgramWithRule/{programName}/{ruleName}")
    public LoyaltyProgram createLoyaltyProgramWithRule(@PathVariable String programName,@PathVariable String ruleName) throws IdConflictException, EntityNotFoundException {
        LoyaltyProgram newProgram= (new LoyaltyProgram(programName, ruleRepository.findByRuleName(ruleName)));
        //checkProgram(newProgram)
        return loyaltyProgramManager.create(newProgram);
    }

    @PostMapping("/enrollActivity/{programName}/{activityEmail}")
    public Activity enrollActivity(@PathVariable String programName, @PathVariable String activityEmail) throws IdConflictException, EntityNotFoundException {
        checkActivity(activityEmail,programName);
        loyaltyProgramRepository.findByProgramName(programName).enrollActivity(activityRepository.findByEmail(activityEmail));
        activityManager.updateWithLoyaltyProgram(activityRepository.findByEmail(activityEmail).getUserId(),loyaltyProgramRepository.findByProgramName(programName));
        return activityRepository.findByEmail(activityEmail);
    }

    @PostMapping("/enrollCostumer/{activityEmail}/{costumerEmail}")
    public Collection<LoyaltyProgram> enrollCostumer(@PathVariable String activityEmail, @PathVariable String costumerEmail) throws EntityNotFoundException, IdConflictException {
        checkEnrollCostumer(activityEmail, costumerEmail);
        activityRepository.findByEmail(activityEmail).getLoyaltyProgram().enrollCostumer(costumerRepository.findByEmail(costumerEmail));
        costumerManager.updateWithLoyaltyProgram(costumerRepository.findByEmail(costumerEmail).getUserId(),activityRepository.findByEmail(activityEmail).getLoyaltyProgram());
       return costumerRepository.findByEmail(costumerEmail).getFidelityCard().getLoyaltyPrograms();
    }
    @Override
    @PostMapping("/unEnrollActivity/{programName}/{activityEmail}")
    public boolean unEnrollActivity(@PathVariable String programName, @PathVariable String activityEmail) throws IdConflictException, EntityNotFoundException {
        loyaltyProgramRepository.findByProgramName(programName).unEnrolledActivity(activityRepository.findByEmail(activityEmail));
        updateActivityAfterUnsubscribed(activityEmail);
        return !loyaltyProgramRepository.findByProgramName(programName).getEnrolledActivities().contains(activityRepository.findByEmail(activityEmail));
    }

    private void updateActivityAfterUnsubscribed(String activityEmail) {
        Activity activity = activityRepository.findByEmail(activityEmail);
        activity.setLoyaltyProgram(null);
        activityRepository.save(activity);
    }


    @GetMapping("/listCostumer/{programName}")
    public Iterable<FidelityCard> getCostumersProgram(@PathVariable  String programName) {
        return loyaltyProgramRepository.findByProgramName(programName).getFidelityCards();
    }

    @PostMapping("/addRule/{programName}/{ruleName}")
    public Set<Rule> addRule(@PathVariable String programName, @PathVariable String ruleName) throws IdConflictException, EntityNotFoundException {
        loyaltyProgramRepository.findByProgramName(programName).addRule(ruleRepository.findByRuleName(ruleName));
        loyaltyProgramManager.updateWithRule(programName, ruleName);
        return loyaltyProgramRepository.findByProgramName(programName).getRules();
    }

    @GetMapping("/getRules/{programName}")
    public Set<Rule> getRule(@PathVariable String programName) throws IdConflictException, EntityNotFoundException {
        return loyaltyProgramRepository.findByProgramName(programName).getRules();
    }

    private void checkActivity(String activityEmail, String programName) throws EntityNotFoundException, IdConflictException{
        if(!activityRepository.existsByEmail(activityEmail)) throw  new EntityNotFoundException("Attività non esistente!");
        if(!loyaltyProgramRepository.existsByProgramName(programName)) throw new EntityNotFoundException("Programma fedeltà non esistente!");
        if(loyaltyProgramRepository.findByProgramName(programName).getEnrolledActivities().contains(activityRepository.findByEmail(activityEmail))) throw new IllegalStateException("Attività già iscritta al programma fedeltà inserito");
    }
    private void checkEnrollCostumer(String activityEmail, String costumerEmail) throws EntityNotFoundException {
        if(!activityRepository.existsByEmail(activityEmail)) throw new EntityNotFoundException("Attività non esistente");
        if(!costumerRepository.existsByEmail(costumerEmail)) throw new EntityNotFoundException("Consumatore non presente");
        checkIfCostumerIsAlreadyEnrolled(activityEmail, costumerEmail);
    }

    private void checkIfCostumerIsAlreadyEnrolled(String activityEmail, String costumerEmail) {
        if(costumerRepository.findByEmail(costumerEmail).getFidelityCard().getLoyaltyPrograms().contains(activityRepository.findByEmail(activityEmail).getLoyaltyProgram()))
            throw new IllegalArgumentException("Consumatore gà iscritto al Programma");
    }


}
