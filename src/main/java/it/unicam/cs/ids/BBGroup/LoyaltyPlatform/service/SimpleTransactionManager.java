package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.ActivityRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleTransactionManager implements TransactionManager{

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private FidelityCardRepository fidelityCardRepository;
    @Autowired
    private FidelityCardManager fidelityCardManager;
    @Autowired
    private ActivityManager activityManager;
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Transaction getInstance(Long id) throws EntityNotFoundException {
        return transactionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(""+id));
    }

    @Override
    public Transaction create(Transaction object) throws EntityNotFoundException, IdConflictException {
        object.setFidelityCard(fidelityCardRepository.findById(object.getFidelityCardId()));
        object.setActivity(activityRepository.findById(object.getActivityId()));
        return transactionRepository.save(object);
    }

    @Override
    public Transaction update(Transaction object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
