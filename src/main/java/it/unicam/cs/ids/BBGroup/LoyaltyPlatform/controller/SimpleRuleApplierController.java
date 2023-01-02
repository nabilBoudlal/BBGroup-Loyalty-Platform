package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.FidelityCardManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.LoyaltyProgramManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.LoyaltyRuleManager;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/RuleApplier")
public class SimpleRuleApplierController {
    @Autowired
    private FidelityCardManager cardManager;
    @Autowired
    private LoyaltyProgramManager programManager;
    @Autowired
    private LoyaltyRuleManager ruleManager;
    @Autowired
    private TransactionManager transactionManager;

    /**
     * It takes a cardId and a transactionId, gets the card and transaction, adds the transaction to the card, validates
     * the transaction, and then applies the rules of the transaction's loyalty program to the card
     *
     * @param cardId The id of the card you want to apply the rule to
     * @param transactionId The id of the transaction to be applied to the card
     * @return The card with the updated points
     */
    @PostMapping("/applyRule/{cardId}/{transactionId}")
    public FidelityCard applyRuleToCard(@PathVariable Long cardId,@PathVariable Long transactionId) throws EntityNotFoundException {
       cardManager.getInstance(cardId).addTransaction(transactionManager.getInstance(transactionId));
       transactionManager.getInstance(transactionId).validate();
       transactionManager.getInstance(transactionId).getLoyaltyProgram().getLoyaltyRules().forEach(s-> {
           try {
               s.applyRule(transactionManager.getInstance(transactionId), cardManager.getInstance(cardId));
           } catch (EntityNotFoundException e) {
               throw new RuntimeException(e);
           }
       });
        return cardManager.getInstance(cardId);
    }

}
