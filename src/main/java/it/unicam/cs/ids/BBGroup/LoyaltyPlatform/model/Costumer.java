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

    public Costumer(String name, String surname, String email, String phone) {
        super(name, surname, email, phone);
    }
}
