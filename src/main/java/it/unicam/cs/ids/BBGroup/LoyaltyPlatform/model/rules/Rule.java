package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Rule(String ruleName, RuleType type) {
        this.ruleName = ruleName;
        this.type = type;
    }

    public abstract int applyRule(Transaction transaction);
}
