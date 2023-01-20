package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SimpleRulePoint extends Rule{

    private final int pointCalculator;

    public SimpleRulePoint() {
        super("regolaAPunti", RuleType.RULE_POINT);
        this.pointCalculator = 1;
    }


    @Override
    public int applyRule(Transaction transaction) {
        return transaction.getPrice()*pointCalculator;
    }
}
