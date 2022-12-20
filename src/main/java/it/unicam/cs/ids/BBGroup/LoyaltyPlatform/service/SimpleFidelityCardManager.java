package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.controller.SimpleCostumerController;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerWalletRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleFidelityCardManager implements FidelityCardManager {

    @Autowired
    private FidelityCardRepository fidelityCardRepository;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private SimpleCostumerManager costumerManager;

    @Autowired
    private CostumerWalletRepository costumerWalletRepository;
    @Autowired
    private SimpleCostumerController costumerController;
    private SimpleCostumerWalletManager costumerWalletManager;

    @Override
    public FidelityCard getInstance(Long id) throws EntityNotFoundException {
        return fidelityCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Nessuna carta con id: " + id + " presente"));
    }

    @Override
    public FidelityCard create(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return fidelityCardRepository.save(object);
    }

    @Override
    public FidelityCard update(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return fidelityCardRepository.existsById(id);
    }



}