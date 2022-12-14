package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_seq")
    @SequenceGenerator(
            name = "activity_seq",
            sequenceName = "activity_seq",
            allocationSize = 1)
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    private String name;

    private String address;

    private String VatCode;

    private String email;

    private String phone;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "activity_admin_activity_admin_id")
    private ActivityAdmin activityAdmin;

    @ManyToOne
    @JoinColumn(name = "loyalty_program_loyalty_program_id")
    private LoyaltyProgram loyaltyProgram;

    public Activity() {
    }

    public Activity(String name, String address, String vatCode, String email, String phone, ActivityAdmin activityAdmin, LoyaltyProgram loyaltyProgram) {
        this.name = name;
        this.address = address;
        VatCode = vatCode;
        this.email = email;
        this.phone = phone;
        this.activityAdmin = activityAdmin;
        this.loyaltyProgram = loyaltyProgram;
    }

    public LoyaltyProgram getLoyaltyProgram() {
        return loyaltyProgram;
    }


    public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }


    public ActivityAdmin getActivityAdmin() {
        return activityAdmin;
    }

    public void setActivityAdmin(ActivityAdmin activityAdmin) {
        this.activityAdmin = activityAdmin;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Activity activity = (Activity) o;
        return activityId != null && Objects.equals(activityId, activity.activityId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}