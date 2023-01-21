package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


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
        super(name, address, email, phone);
        this.vatCode = vatCode;
        }

}

