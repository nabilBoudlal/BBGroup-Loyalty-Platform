package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityAdmin;
import org.springframework.data.repository.CrudRepository;

public interface ActivityAdminRepository extends CrudRepository<ActivityAdmin, Long> {
    ActivityAdmin findByEmail(String email);
    ActivityAdmin findByEmailLike(String email);
    ActivityAdmin findByActivityAdminId(Long activityAdminId);
}