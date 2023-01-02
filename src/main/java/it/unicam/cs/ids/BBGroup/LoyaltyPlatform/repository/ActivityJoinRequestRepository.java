package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.ActivityJoinRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ActivityJoinRequestRepository extends CrudRepository<ActivityJoinRequest, Long> {
    @Query("select (count(a) > 0) from ActivityJoinRequest a where a.activityEmail = ?1")
    boolean existsByEmail(String activityEmail);
}