package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
/*
 * This class represent a standard level rule with 3 threshold
 */
@Entity
@Getter
@Setter
public class SimpleRuleLevel extends SimpleRulePoint {
    private final int levels;
    @Transient
    Map<String, Integer> thresholds = new HashMap<>();

    public SimpleRuleLevel() {
        this.setType(RuleType.RULE_LEVEL);
        this.setRuleName("regolaALivelli");
        this.levels = 3;
        thresholds.put("Base", 100);
        thresholds.put("Intermedio", 300);
        thresholds.put("Avanzato", 500);
    }

    @Override
    public void applyRule(Transaction transaction) {
        super.applyRule(transaction);
        if(transaction.getFidelityCard().getTotalPoints() <= thresholds.get("Base")) {
            transaction.getFidelityCard().setLevel("Base");
        } else if(transaction.getFidelityCard().getTotalPoints() > thresholds.get("Base") && transaction.getFidelityCard().getTotalPoints()<= thresholds.get("Intermedio")) {
            transaction.getFidelityCard().setLevel("Intermedio");
        } else {
            transaction.getFidelityCard().setLevel("Avanzato");
        }
    }
}
