package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("DefaultRule")
public class DefaultEarningPointRule extends LoyaltyRule{

    private final LoyaltyRuleType ruleType = LoyaltyRuleType.POINTS;
    private int pointsCalculator;


    public DefaultEarningPointRule(int pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    @Override
    public int applyRule(Transaction transaction){return pointsCalculator*transaction.getPrice();}

}
