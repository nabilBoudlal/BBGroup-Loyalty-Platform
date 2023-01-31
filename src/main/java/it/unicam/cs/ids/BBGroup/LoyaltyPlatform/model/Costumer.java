package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Costumer extends User{

    private String surname;

    @OneToOne(mappedBy = "costumer", orphanRemoval = true)
    @JsonIgnore
    private FidelityCard fidelityCard;

    public Costumer(String name, String surname, String address, String email, String phone) {
        super(name, address, email, phone);
        this.surname = surname;
    }

    public void addCard(FidelityCard card) {
        this.fidelityCard=card;
    }

}
