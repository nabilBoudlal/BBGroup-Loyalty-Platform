package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Activity extends User {

    private String vatCode;

    @ManyToOne
    @JoinColumn(name = "loyalty_program_loyalty_program_id")
    private LoyaltyProgram loyaltyProgram;


    public Activity(String name, String email, String vatCode, String address, String phone) {
        super(name, address, email, phone);
        this.vatCode = vatCode;
    }
}

