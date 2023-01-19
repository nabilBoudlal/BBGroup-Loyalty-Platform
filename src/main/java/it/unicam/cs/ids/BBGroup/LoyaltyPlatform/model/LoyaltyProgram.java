package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

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
public abstract class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loyalty_program_id", nullable = false)
    private Long loyaltyProgramId;

    @ManyToMany
    @JoinTable(name = "loyalty_program_fidelity_cards",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "fidelity_cards_card_id"))
    private Set<FidelityCard> fidelityCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    private Set<Activity> enrolledActivities = new LinkedHashSet<>();


}
