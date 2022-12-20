package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Activity findByAdminEmail(String adminEmail);
    boolean existsByActivityId(Long activityId);
    void deleteByActivityId(Long activityId);
    boolean existsByVatCode(String vatCode);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}