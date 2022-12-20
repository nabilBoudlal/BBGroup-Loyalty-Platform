package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}