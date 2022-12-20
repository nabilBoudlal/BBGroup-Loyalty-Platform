package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISCRIMINATOR", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("AbstractRule")
public abstract class LoyaltyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    private  LoyaltyRuleType ruleType;


    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "loyalty_rule_loyalty_programs",
            joinColumns = @JoinColumn(name = "loyalty_rule_rule_id"),
            inverseJoinColumns = @JoinColumn(name = "loyalty_programs_loyalty_program_id"))
    private Set<LoyaltyProgram> loyaltyPrograms = new LinkedHashSet<>();


    public LoyaltyRule() {
    }

    /**
     * > This function takes a transaction and returns a number that represents the rule that was applied to the
     * transaction
     *
     * @param transaction The transaction object that is being processed.
     */
    public abstract void applyRule(Transaction transaction, FidelityCard card);

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
