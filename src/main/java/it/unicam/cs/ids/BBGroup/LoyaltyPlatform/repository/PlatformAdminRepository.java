package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.PlatformAdmin;
import org.springframework.data.repository.CrudRepository;

public interface PlatformAdminRepository extends CrudRepository<PlatformAdmin, Long> {
    boolean existsByEmail(String email);
    PlatformAdmin findByEmail(String email);
}