package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.LoyaltyProgram;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    private String ruleName;

    private RuleType type;

    @ManyToMany(mappedBy = "rules")
    private Set<LoyaltyProgram> loyaltyPrograms = new LinkedHashSet<>();


    public Rule(String ruleName, RuleType type) {
        this.ruleName = ruleName;
        this.type = type;
    }

    public abstract void applyRule(Transaction transaction);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rule rule = (Rule) o;
        return ruleId != null && Objects.equals(ruleId, rule.ruleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
