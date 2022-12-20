package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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


    @ManyToOne(optional = false)
    @JoinColumn(name = "activity_admin_id", nullable = false)
    @JsonIgnore
    private ActivityAdmin activityAdmin;

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    @JsonIgnore
    private Set<Activity> activities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    @JsonIgnore
    private Set<FidelityCard> fidelityCards = new LinkedHashSet<>();



    private String creatorEmail;


    @ManyToMany
    @JoinTable(name = "loyalty_program_loyalty_rules",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "loyalty_rules_rule_id"))
    private Set<LoyaltyRule> loyaltyRules = new LinkedHashSet<>();


    public LoyaltyProgram(String programName, ActivityAdmin activityAdmin) {
        this.programName = programName;
        this.activityAdmin = activityAdmin;
    }

    public LoyaltyProgram(String programName, String creatorEmail) {
        this.programName = programName;
        this.creatorEmail= creatorEmail;
    }

    public void enrollCard(FidelityCard fidelityCard){
        this.fidelityCards.add(fidelityCard);
        fidelityCard.setLoyaltyProgram(this);
    }

    public void enrollActivity(Activity activity){
        this.activities.add(activity);
    }

    public void addRule(LoyaltyRule rule){
        this.loyaltyRules.add(rule);
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "loyaltyProgramId = " + loyaltyProgramId + ", " +
                "programName = " + programName + ", " +
                "activityAdmin = " + activityAdmin + ")";
    }
}