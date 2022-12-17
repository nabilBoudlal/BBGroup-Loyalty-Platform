package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.CostumerWallet;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerWalletRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleCostumerWalletManager implements CostumerWalletManager{

    @Autowired
    private CostumerWalletRepository walletRepository;
    private CostumerRepository costumerRepository;
    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    @Override
    public CostumerWallet getInstance(Long id) throws EntityNotFoundException {
        return walletRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Portafogli con id: "+id+" non trovato"));
    }

    @Override
    public CostumerWallet create(CostumerWallet object) throws EntityNotFoundException, IdConflictException {
        return walletRepository.save(object);
    }

    @Override
    public CostumerWallet update(CostumerWallet object) throws EntityNotFoundException, IdConflictException {
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
