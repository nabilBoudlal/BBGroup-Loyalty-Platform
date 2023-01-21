package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.repository;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Long> {
    List<Rule> findByLoyaltyPrograms_ProgramName(String programName);

    @Query("""
            select r from Rule r inner join r.loyaltyPrograms loyaltyPrograms
            where loyaltyPrograms.loyaltyProgramId = ?1""")
    List<Rule> findByLoyaltyPrograms_LoyaltyProgramId(Long loyaltyProgramId);
    Rule findByRuleName(String ruleName);
}