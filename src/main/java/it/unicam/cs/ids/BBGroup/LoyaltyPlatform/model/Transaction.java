package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    private  long activityId;
    private  long fidelityCardId;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionGenerator")
    @SequenceGenerator(name = "transactionGenerator", allocationSize = 1)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    private int price;

    private boolean validated;

    @ManyToOne
    @JoinColumn(name = "fidelity_card_card_id")
    @JsonIgnore
    private FidelityCard fidelityCard;

    @OneToOne(orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "activity_user_id")
    private Activity activity;

    public Transaction(int price, boolean validated, FidelityCard fidelityCard, Activity activity) {
        this.price = price;
        this.validated = false;
        this.fidelityCard = fidelityCard;
        this.activity = activity;
    }
    public Transaction(int price, long fidelityCardId, long activityId) {
        this.price = price;
        this.validated = false;
        this.fidelityCardId = fidelityCardId;
        this.activityId = activityId;
    }

    public Transaction(int price, long activityId) {
        this.price = price;
        this.validated = false;
        this.activityId = activityId;
    }



    public boolean validate(){ return this.validated= true;}


}
