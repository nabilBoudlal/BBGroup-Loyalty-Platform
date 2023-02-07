package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.CostumerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/Costumer")
public class SimpleCostumerController implements CostumerController{

    @Autowired
    private CostumerManager costumerManager;

    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @GetMapping("/{id}")
    @Override
    public Costumer getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return costumerManager.getInstance(id);
    }

    @Override
    public Costumer create(Costumer object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public Costumer update(Costumer object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return costumerManager.delete(id);
    }

    @GetMapping("/exists/{id}")
    @Override
    public boolean exists(@PathVariable Long id) {return costumerManager.exists(id);}

    @GetMapping("/totalPoints/{costumerEmail}")
    public int getTotalCardPoint(@PathVariable String costumerEmail){
        return costumerRepository.findByEmail(costumerEmail).getFidelityCard().getTotalPoints();
    }

    @GetMapping("/listTransactions/{costumerEmail}")
    public Set<Transaction> getListTransactions(@PathVariable String costumerEmail){
        return costumerRepository.findByEmail(costumerEmail).getFidelityCard().getTransactions();
    }
    @GetMapping("/listPrograms/{costumerEmail}")
    public List<LoyaltyProgram> getEnrolledToPrograms(@PathVariable String costumerEmail) {
        return loyaltyProgramRepository.findByFidelityCards_Costumer(costumerRepository.findByEmail(costumerEmail));
    }

}
