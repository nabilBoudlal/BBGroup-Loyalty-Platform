package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;

@Entity
@Table(name = "costumer_wallet")
public class CostumerWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costumer_wallet_seq")
    @SequenceGenerator
            (name = "costumer_wallet_seq",
                    sequenceName = "costumer_wallet_seq",
                    allocationSize = 1)
    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "fidelity_card_fidelity_card_id")
    private FidelityCard fidelityCard;



    public CostumerWallet() {
    }

    public CostumerWallet(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }

    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }

}