package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    private int price;

    private boolean validated;

    @ManyToOne
    @JoinColumn(name = "fidelity_card_card_id")
    private FidelityCard fidelityCard;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "activity_user_id")
    private Activity activity;

    public Transaction(int price, boolean validated, FidelityCard fidelityCard, Activity activity) {
        this.price = price;
        this.validated = false;
        this.fidelityCard = fidelityCard;
        this.activity = activity;
    }

    public void validate(){this.validated= true;}
}
