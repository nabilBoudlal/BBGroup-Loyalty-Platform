package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import org.springframework.data.repository.CrudRepository;

public interface CostumerRepository extends CrudRepository<Costumer, Long> {
    Costumer findByEmail(String email);
    boolean existsByCostumerId(Long costumerId);
    Costumer findByCostumerId(Long costumerId);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}