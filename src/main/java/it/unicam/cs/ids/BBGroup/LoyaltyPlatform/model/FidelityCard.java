package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
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

    @ManyToMany
    @JoinTable(name = "fidelity_card_loyalty_programs",
            joinColumns = @JoinColumn(name = "fidelity_card_fidelity_card_id"),
            inverseJoinColumns = @JoinColumn(name = "loyalty_programs_loyalty_program_id"))
    private Set<LoyaltyProgram> loyaltyPrograms = new LinkedHashSet<>();

    @OneToOne(mappedBy = "fidelityCard", orphanRemoval = true)
    private CostumerWallet costumerWallet;

    public CostumerWallet getCostumerWallet() {
        return costumerWallet;
    }





    public void setCostumerWallet(CostumerWallet costumerWallet) {
        this.costumerWallet = costumerWallet;
    }


    public Set<LoyaltyProgram> getLoyaltyPrograms() {
        return loyaltyPrograms;
    }

    public void setLoyaltyPrograms(Set<LoyaltyProgram> loyaltyPrograms) {
        this.loyaltyPrograms = loyaltyPrograms;
    }

    public Long getFidelityCardId() {
        return fidelityCardId;
    }

    public void setFidelityCardId(Long fidelityCardId) {
        this.fidelityCardId = fidelityCardId;
    }

}