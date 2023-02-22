package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.*;

/*
 * This class represent an Activity, which is also an entity in the db
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Activity extends User {

    private String vatCode;

    @ManyToOne
    @JoinColumn(name = "loyalty_program")
    private LoyaltyProgram loyaltyProgram;

    private String programName;


    public Activity(String name, String email, String vatCode, String address, String phone) {
        super(name, "",address, email, phone);
        this.vatCode = vatCode;
        }

}

