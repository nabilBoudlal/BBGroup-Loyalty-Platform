package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.CostumerJoinRequest;
import org.springframework.data.repository.CrudRepository;

public interface CostumerJoinRequestRepository extends CrudRepository<CostumerJoinRequest, Long> {
    boolean existsByPhone(String phone);
    boolean existsByCostumerEmail(String costumerEmail);
}