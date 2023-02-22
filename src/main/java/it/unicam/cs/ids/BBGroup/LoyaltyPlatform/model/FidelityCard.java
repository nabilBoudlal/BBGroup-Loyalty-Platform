package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class FidelityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fidelityCardGenerator")
    @SequenceGenerator(name = "fidelityCardGenerator", allocationSize = 1)
    @Column(name = "card_id", nullable = false)
    private Long cardId;

    private int totalPoints;

    private String level;
    private double cashback;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "costumer_user_id", unique = true)
    private Costumer costumer;

    @ManyToMany(mappedBy = "fidelityCards")
    private Set<LoyaltyProgram> loyaltyPrograms = new HashSet<>();

    @OneToMany(mappedBy = "fidelityCard", orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();

    public FidelityCard(Costumer costumer) {
        this.costumer = costumer;
        totalPoints=0;
        cashback=0.0;
    }

    public void addPoints(int value) {
        totalPoints+=value;
    }

    public int removePoints(int value) {
        totalPoints-=value;
        return totalPoints;
    }

    public void addLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        this.loyaltyPrograms.add(loyaltyProgram);
    }

    public void addCashback(double value) {this.cashback += value;}
}
