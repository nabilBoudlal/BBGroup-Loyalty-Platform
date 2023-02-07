package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Activity;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.FidelityCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FidelityCardRepository extends CrudRepository<FidelityCard, Long> {
    List<FidelityCard> findByLoyaltyPrograms_ProgramName(String programName);

    @Transactional
    @Modifying
    @Query("update FidelityCard f set f.totalPoints = ?1 where f.totalPoints = ?2")
    int updateTotalPointsByTotalPoints(int totalPoints, int totalPoints1);
    FidelityCard findById(long fidelityCardId);
}