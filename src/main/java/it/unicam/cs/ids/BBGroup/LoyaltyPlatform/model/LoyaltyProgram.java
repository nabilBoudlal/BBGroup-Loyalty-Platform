package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "loyalty_program")
public class LoyaltyProgram {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loyalty_program_seq")

    @SequenceGenerator(
            name = "loyalty_program_seq",
            sequenceName = "loyalty_program_seq",
            allocationSize = 1)
    @Column(name = "loyalty_program_id", nullable = false)
    private Long loyaltyProgramId;

    private String programName;

    private int multiplier;

    private int thresholdCounter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "activity_admin_id", nullable = false)
    private ActivityAdmin activityAdmin;

    @ManyToMany(mappedBy = "loyaltyPrograms")
    private Set<FidelityCard> fidelityCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    private Set<Activity> activities = new LinkedHashSet<>();

    public LoyaltyProgram() {
    }


    public LoyaltyProgram(String programName, ActivityAdmin activityAdmin, Set<FidelityCard> fidelityCards, Set<Activity> activities) {
        this.programName = programName;
        this.activityAdmin = activityAdmin;
        this.fidelityCards = fidelityCards;
        this.activities = activities;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<FidelityCard> getFidelityCards() {
        return fidelityCards;
    }

    public void setFidelityCards(Set<FidelityCard> fidelityCards) {
        this.fidelityCards = fidelityCards;
    }


    public Long getLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public void setLoyaltyProgramId(Long loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getThresholdCounter() {
        return thresholdCounter;
    }

    public void setThresholdCounter(int thresholdCounter) {
        this.thresholdCounter = thresholdCounter;
    }

    public ActivityAdmin getActivityAdmin() {
        return activityAdmin;
    }

    public void setActivityAdmin(ActivityAdmin activityAdmin) {
        this.activityAdmin = activityAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoyaltyProgram that = (LoyaltyProgram) o;
        return loyaltyProgramId != null && Objects.equals(loyaltyProgramId, that.loyaltyProgramId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}