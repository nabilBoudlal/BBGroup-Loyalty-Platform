package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class PlatformAdmin extends User{


    public PlatformAdmin(String name, String surname, String email, String phone) {
        super(name, surname, email, phone);
    }


}
