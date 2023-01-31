package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Transactional
    @Modifying
    @Query("update Transaction t set t.validated = ?1 where t.validated = ?2")
    int updateValidatedByValidated(boolean validated, boolean validated1);
}