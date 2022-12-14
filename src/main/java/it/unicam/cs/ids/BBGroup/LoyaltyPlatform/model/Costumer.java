package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Costumer extends User{

    private String surname;

    public Costumer(String name, String surname, String address, String email, String phone) {
        super(name, address, email, phone);
        this.surname = surname;
    }
}
