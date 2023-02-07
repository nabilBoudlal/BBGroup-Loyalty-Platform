package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Costumer;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoyaltyProgramRepository extends CrudRepository<LoyaltyProgram, Long> {
    List<LoyaltyProgram> findByFidelityCards_Costumer(Costumer costumer);
    boolean existsByProgramName(String programName);
    LoyaltyProgram findByProgramName(String programName);
}