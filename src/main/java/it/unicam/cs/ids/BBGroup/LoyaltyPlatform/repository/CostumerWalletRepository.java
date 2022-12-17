package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.CostumerWallet;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CostumerWalletRepository extends CrudRepository<CostumerWallet, Long> {

    CostumerWallet findByWalletId(Long walletId);
}