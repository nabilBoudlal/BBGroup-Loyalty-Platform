package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.LoyaltyProgramRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.RuleRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.TransactionRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.FidelityCardManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/Transaction")
public class SimpleTransactionController implements TransactionController{
    @Autowired
    private TransactionManager transactionManager;
    @Autowired
    private FidelityCardRepository fidelityCardRepository;
    @Autowired
    private FidelityCardManager cardManager;
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/{id}")
    @Override
    public Transaction getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return transactionManager.getInstance(id);
    }
    @PostMapping("/createNew")
    @Override
    public Transaction create(@RequestBody Transaction object) throws EntityNotFoundException, IdConflictException {
        return transactionManager.create(object);
    }

    @Override
    public Transaction update(Transaction object) throws EntityNotFoundException, IdConflictException {
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

    // A method that is called when a transaction is validated. It takes the card and transaction id as parameters and
    // returns the total points of the card.
    @PostMapping("/validateTransaction/{cardId}/{transactionId}")
    public List<Rule> validateTransaction(@PathVariable Long cardId, @PathVariable Long transactionId) throws EntityNotFoundException {
       Transaction transaction= transactionManager.getInstance(transactionId);
        List<Rule> currentRules= ruleRepository.findByLoyaltyPrograms_LoyaltyProgramId(transaction.getActivity().getLoyaltyProgram().getLoyaltyProgramId());
      // List<Rule> currentRules= ruleRepository.findByLoyaltyPrograms_ProgramName(transaction.getActivity().getProgramName());
       currentRules.forEach(s -> s.applyRule(transaction));
       return currentRules;
    }


}
