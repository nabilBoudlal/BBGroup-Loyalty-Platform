package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "fidelity_card")
public class FidelityCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fidelity_card_seq")
    @SequenceGenerator(
            name = "fidelity_card_seq",
            sequenceName = "fidelity_card_seq",
            allocationSize = 1)
    @Column(name = "fidelity_card_id", nullable = false)
    private Long fidelityCardId;


    @OneToOne(mappedBy = "fidelityCard", orphanRemoval = true)
    @JsonIgnore
    private CostumerWallet costumerWallet;


    @ManyToOne
    @JoinColumn(name = "loyalty_program_loyalty_program_id")
    private LoyaltyProgram loyaltyProgram;


    @OneToMany(mappedBy = "fidelityCard", orphanRemoval = true)
    @JsonIgnore
    private Set<Transaction> transactions = new LinkedHashSet<>();

    private int totalPoints;


    public FidelityCard(CostumerWallet costumerWallet){
        this.costumerWallet=costumerWallet;
    }

    public void enrollCardToLoyaltyProgram(LoyaltyProgram loyaltyProgram){
        loyaltyProgram.enrollCard(this);
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void addPoints(int pointsToAdd){this.totalPoints+= pointsToAdd;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FidelityCard that = (FidelityCard) o;
        return fidelityCardId != null && Objects.equals(fidelityCardId, that.fidelityCardId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "fidelityCardId = " + fidelityCardId + ", " +
                "costumerWallet = " + costumerWallet + ")";
    }
}