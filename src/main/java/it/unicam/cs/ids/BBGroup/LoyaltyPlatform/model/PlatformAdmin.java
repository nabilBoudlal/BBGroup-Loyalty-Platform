package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class PlatformAdmin extends User{

    private String surname;


    public PlatformAdmin(String name, String surname, String address, String email, String phone) {
        super(name, address, email, phone);
        this.surname = surname;
    }



}
