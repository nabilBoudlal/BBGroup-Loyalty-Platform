package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Entity
public abstract class LoyaltyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    private  LoyaltyRuleType ruleType;

    @ManyToOne
    @JoinColumn(name = "loyalty_program_loyalty_program_id")
    private LoyaltyProgram loyaltyProgram;


    public LoyaltyRule() {
    }

    /**
     * > This function takes a transaction and returns a number that represents the rule that was applied to the
     * transaction
     *
     * @param transaction The transaction object that is being processed.
     * @return The return value is the number of times the rule was applied.
     */
    abstract int applyRule(Transaction transaction);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoyaltyRule that = (LoyaltyRule) o;
        return ruleId != null && Objects.equals(ruleId, that.ruleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
