package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;

@Entity
@Table(name = "costumer")
public class Costumer implements User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costumer_seq")
    @SequenceGenerator(
            name = "costumer_seq",
            sequenceName = "costumer_seq",
            allocationSize = 1)
    @Column(name = "costumer_id", nullable = false)
    private Long costumerId;

    private String name;

    private String surname;

    private String email;

    private String phone;


    private final UserType userType = UserType.COSTUMER;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "costumer_wallet_wallet_id")
    private CostumerWallet costumerWallet;

    public Costumer(){
    }

    public Costumer(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Long getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Long costumerId) {
        this.costumerId = costumerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}