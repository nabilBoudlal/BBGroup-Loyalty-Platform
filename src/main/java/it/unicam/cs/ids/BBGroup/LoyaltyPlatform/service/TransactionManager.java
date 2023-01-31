package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;

public interface TransactionManager extends EntityManager<Transaction, Long>{
    void updateStatus(Long transactionId) throws EntityNotFoundException;
}
