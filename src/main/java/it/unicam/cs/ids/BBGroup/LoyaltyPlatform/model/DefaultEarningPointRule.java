package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultEarningPointRule extends LoyaltyRule{

    private final LoyaltyRuleType ruleType = LoyaltyRuleType.POINTS;
    private int pointsCalculator;

    public DefaultEarningPointRule(int pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }


    @Override
    public int applyRule(Transaction transaction){return pointsCalculator*transaction.getPrice();}

}
