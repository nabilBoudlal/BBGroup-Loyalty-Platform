package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.ActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Activity")
public class SimpleActivityController implements ActivityController{

    @Autowired
    public ActivityManager activityManager;
    @Autowired
    public ActivityRepository activityRepository;
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;
    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    @Autowired
    private LoyaltyProgramController loyaltyProgramController;

    @GetMapping("/{id}")
    @Override
    public Activity getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return activityManager.getInstance(id);
    }

    @PostMapping("/create")
    @Override
    public Activity create(Activity object) throws EntityNotFoundException, IdConflictException {
        return activityManager.create(object);
    }

    @Override
    public Activity update(Activity object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        loyaltyProgramController.unEnrollActivity(this.getInstance(id).getProgramName(), this.getInstance(id).getEmail());
        return activityManager.delete(id);
    }

    @Override
    public boolean exists(Long id) {return activityManager.exists(id);}

    @GetMapping("/listEnrolledCostumers/{activityEmail}")
    public List<Costumer> getEnrolledCostumers(@PathVariable String activityEmail){
        List<Costumer> costumers= new ArrayList<>();
        List<FidelityCard> cards= fidelityCardRepository.findByLoyaltyPrograms_ProgramName(activityRepository.findByEmail(activityEmail).getProgramName());
        cards.forEach(s -> costumers.add(s.getCostumer()));
        return costumers;
    }

}
