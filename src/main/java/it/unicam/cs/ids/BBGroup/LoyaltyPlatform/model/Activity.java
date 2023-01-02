package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Activity extends User {

    private String activityEmail;

    private String activityName;

    private String vatCode;

    private String address;


    public Activity(String activityEmail, String activityName, String vatCode, String address, String phone) {
        super(activityName, " ", activityEmail, phone);
        this.vatCode = vatCode;
        this.address = address;
    }
}

