package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class represent a platform admin, which is also an entity in the db
 */

@Entity
@NoArgsConstructor
@Setter
@Getter
public class PlatformAdmin extends User{



    public PlatformAdmin(String name, String surname, String email, String phone) {
        super(name, surname, null,email, phone);
    }


}
