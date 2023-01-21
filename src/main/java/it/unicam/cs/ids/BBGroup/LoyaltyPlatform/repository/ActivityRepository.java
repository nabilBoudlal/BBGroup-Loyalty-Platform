package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    @Transactional
    @Modifying
    @Query("update Activity a set a.loyaltyProgram = ?1")
    int updateLoyaltyProgramBy(LoyaltyProgram loyaltyProgram);

    Activity findByEmail(String email);
    boolean existsByEmail(String email);

    Activity findById(long activityId);
}