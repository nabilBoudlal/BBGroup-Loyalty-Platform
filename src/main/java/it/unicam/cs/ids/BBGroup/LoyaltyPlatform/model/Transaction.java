package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;


    private int price;

    private boolean isValidated;

    @ManyToOne(optional = false)
    @JoinColumn(name = "loyalty_program_loyalty_program_id", nullable = false)
    private LoyaltyProgram loyaltyProgram;

    public LoyaltyProgram getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
