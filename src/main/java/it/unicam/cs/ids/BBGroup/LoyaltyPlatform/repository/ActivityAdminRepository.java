package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityAdmin;
import org.springframework.data.repository.CrudRepository;

public interface ActivityAdminRepository extends CrudRepository<ActivityAdmin, Long> {
    ActivityAdmin findByEmail(String email);
    boolean existsByActivityAdminId(Long activityAdminId);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

}