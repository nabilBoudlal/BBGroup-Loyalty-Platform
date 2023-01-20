package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Transaction")
public class SimpleTransactionController implements TransactionController{
    @Autowired
    private TransactionManager transactionManager;
    @GetMapping("/{id}")
    @Override
    public Transaction getInstance(Long id) throws EntityNotFoundException {
        return transactionManager.getInstance(id);
    }
    @PostMapping("/createNew")
    @Override
    public Transaction create(Transaction object) throws EntityNotFoundException, IdConflictException {
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
}
