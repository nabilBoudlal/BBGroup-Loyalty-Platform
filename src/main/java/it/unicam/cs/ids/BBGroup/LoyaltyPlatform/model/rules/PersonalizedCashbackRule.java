package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.Transaction;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class represent a cashback rule that extend a standard point rule
 * The discount can be personalized
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PersonalizedCashbackRule extends SimpleRulePoint {

    private int discount;

    public PersonalizedCashbackRule(int discount) {
        this.setType(RuleType.RULE_CASHBACK);
        this.setRuleName("regolaACashback");
        this.discount= discount;
    }
    @Override
    public void applyRule(Transaction transaction) {
        super.applyRule(transaction);
        transaction.getFidelityCard().addCashback(Math.round(transaction.getPrice() * (double) discount / 100));
    }
}
