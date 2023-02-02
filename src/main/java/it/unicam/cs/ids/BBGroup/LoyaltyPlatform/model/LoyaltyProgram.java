package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
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
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyaltyProgramGenerator")
    @SequenceGenerator(name = "loyaltyProgramGenerator", allocationSize = 1)
    @Column(name = "loyalty_program_id", nullable = false)
    private Long loyaltyProgramId;

    private String programName;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "loyalty_program_fidelity_cards",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "fidelity_cards_card_id"))
    private Set<FidelityCard> fidelityCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    @JsonIgnore
    private Set<Activity> enrolledActivities = new LinkedHashSet<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "loyalty_program_rules",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "rules_rule_id"))
    private Set<Rule> rules = new LinkedHashSet<>();

    public LoyaltyProgram(String programName) {
        this.programName = programName;
    }

    public LoyaltyProgram(String programName, Rule rule) {
        this.programName = programName;
        this.rules.add(rule);
    }


    public boolean addRule(Rule rule) {
        rule.addLoyaltyProgram(this);
        return rules.add(rule);
    }

    public void enrollActivity(Activity activity){
        this.enrolledActivities.add(activity);
        activity.setLoyaltyProgram(this);
        activity.setProgramName(this.programName);
    }

    public void enrollCostumer(Costumer costumer) {
        this.fidelityCards.add(costumer.getFidelityCard());
        costumer.getFidelityCard().addLoyaltyProgram(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoyaltyProgram that = (LoyaltyProgram) o;
        return loyaltyProgramId != null && Objects.equals(loyaltyProgramId, that.loyaltyProgramId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
