package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Transaction")
public class SimpleTransactionController implements TransactionController{
    @Autowired
    private TransactionManager transactionManager;

    @Override
    @GetMapping("/{id}")
    public Transaction getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return transactionManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public Transaction create(@RequestBody Transaction object) throws EntityNotFoundException, IdConflictException {
        return transactionManager.create(object);
    }

    @Override
    public Transaction update(Transaction object) throws IdConflictException, EntityNotFoundException {
        return null;
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return transactionManager.delete(id);
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
