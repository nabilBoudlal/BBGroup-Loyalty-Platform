package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "loyalty_program_loyalty_program_id", nullable = false)
    private LoyaltyProgram loyaltyProgram;

    private  String programName;

    @ManyToOne
    @JoinColumn(name = "fidelity_card_fidelity_card_id")
    private FidelityCard fidelityCard;
    private int price;

    private boolean isValidated = false;

    public Transaction(LoyaltyProgram program, int price){
        this.loyaltyProgram= program;
        this.price=price;
    }
    public Transaction(String programName, int price){
        this.programName= programName;
        this.price=price;
    }

    public void validate(){
        this.isValidated=true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return transactionId != null && Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
