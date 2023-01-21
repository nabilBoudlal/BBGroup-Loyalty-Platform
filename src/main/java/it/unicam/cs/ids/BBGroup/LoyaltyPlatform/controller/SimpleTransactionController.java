package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.FidelityCardManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Transaction")
public class SimpleTransactionController implements TransactionController{
    @Autowired
    private TransactionManager transactionManager;
    @Autowired
    private FidelityCardRepository fidelityCardRepository;
    @Autowired
    private FidelityCardManager cardManager;

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

    public int validateTransaction(@PathVariable Long cardId, @PathVariable Long transactionId) throws EntityNotFoundException {
       FidelityCard card=  cardManager.getInstance(cardId);
       Transaction transaction= transactionManager.getInstance(transactionId);
       LoyaltyProgram currentProgram= transaction.getActivity().getLoyaltyProgram();
       currentProgram.getRules().forEach(s -> s.applyRule(transaction));
       return card.getTotalPoints();
    }
}
