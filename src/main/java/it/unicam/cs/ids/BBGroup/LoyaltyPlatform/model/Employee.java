package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * This class represent an Employee, which is also an entity in the db
 */
@Entity
@NoArgsConstructor
@Setter
@Getter

public class Employee extends User{

    private String surname;



    public Employee(String name, String surname, String address, String email, String phone) {
        super(name,surname, address, email, phone);
    }


}
