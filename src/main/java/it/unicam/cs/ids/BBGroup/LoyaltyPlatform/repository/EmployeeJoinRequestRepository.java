package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.EmployeeJoinRequest;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeJoinRequestRepository extends CrudRepository<EmployeeJoinRequest, Long> {
    boolean existsByPhone(String phone);
    boolean existsByEmployeeEmail(String employeeEmail);
}