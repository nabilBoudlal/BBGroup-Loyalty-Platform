package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model.rules.Rule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loyalty_program_id", nullable = false)
    private Long loyaltyProgramId;

    private String programName;

    @ManyToMany
    @JoinTable(name = "loyalty_program_fidelity_cards",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "fidelity_cards_card_id"))
    private Set<FidelityCard> fidelityCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    private Set<Activity> enrolledActivities = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "loyalty_program_rules",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "rules_rule_id"))
    private Set<Rule> rules = new LinkedHashSet<>();

    public LoyaltyProgram(String programName) {
        this.programName = programName;
    }

    public boolean addRule(Rule rule) {
       return rules.add(rule);
    }
}
