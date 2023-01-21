package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import org.springframework.data.repository.CrudRepository;

public interface LoyaltyProgramRepository extends CrudRepository<LoyaltyProgram, Long> {
    LoyaltyProgram findByProgramName(String programName);
}