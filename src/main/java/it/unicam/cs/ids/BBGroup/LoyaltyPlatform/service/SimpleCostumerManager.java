package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.service;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.EntityNotFoundException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.exception.IdConflictException;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.CostumerWallet;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.CostumerWalletRepository;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository.FidelityCardRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.Objects;

@Validated
@Service
public class SimpleCostumerManager implements CostumerManager{
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private CostumerWalletRepository walletRepository;
    @Autowired
    private FidelityCardRepository fidelityCardRepository;
    @Autowired
    private SimpleCostumerWalletManager walletManager;


    @Override
    public Costumer getInstance(@Valid @NotNull Long id) throws EntityNotFoundException {
        return costumerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nessun cliente con l'Id inserito"+id));
    }

    @Override
    public Costumer create(Costumer object) throws EntityNotFoundException, IdConflictException{
        checkFieldsAreNotNull(object);
        checkCostumer(object);
        CostumerWallet wallet= new CostumerWallet();
        object.setCostumerWallet(walletRepository.save(wallet));
        object.getCostumerWallet().setFidelityCard(fidelityCardRepository.save(new FidelityCard()));
        return costumerRepository.save(object);
    }

    @Override
    public Costumer update(Costumer object) throws EntityNotFoundException, IdConflictException {
     if(!costumerRepository.existsByEmail(object.getEmail())) throw new EntityNotFoundException("Nessun cliente con email: "+object.getEmail()+" presente");
     return costumerRepository.save(object);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if(!this.exists(id)) throw new EntityNotFoundException("Nessun cliente con id: "+id+" presente");
        costumerRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return costumerRepository.existsByCostumerId(id);
    }

    private void checkCostumer(Costumer costumer) throws EntityNotFoundException, IdConflictException {
        if(costumerRepository.existsByEmail(costumer.getEmail()))
            throw new IdConflictException("Un cliente con email " +costumer.getEmail()+" è già presente");
        if(costumerRepository.existsByPhone(costumer.getPhone()))
            throw new IdConflictException("Un cliente con telefono " +costumer.getPhone()+" è già presente");
    }

    private void checkFieldsAreNotNull(Costumer costumer) throws NullPointerException{
        Objects.requireNonNull(costumer.getEmail(), "Inserire email valida");
        Objects.requireNonNull(costumer.getPhone(),"Inserire telefono valido");
    }

    public void createNewBlankFidelityCard(String costumerEmail) throws EntityNotFoundException{
    }

}
