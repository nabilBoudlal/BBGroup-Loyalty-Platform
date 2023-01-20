package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class FidelityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id", nullable = false)
    private Long cardId;

    private int totalPoints;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "costumer_user_id", unique = true)
    private Costumer costumer;

    @ManyToMany(mappedBy = "fidelityCards")
    private Set<LoyaltyProgram> loyaltyPrograms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fidelityCard", orphanRemoval = true)
    private Set<Transaction> transactions = new LinkedHashSet<>();

    public FidelityCard(Costumer costumer) {
        this.costumer = costumer;
        totalPoints=0;
    }

    public int addPoints(int value) {
        totalPoints+=value;
        return totalPoints;
    }

    public int removePoints(int value) {
        totalPoints-=value;
        return totalPoints;
    }

}