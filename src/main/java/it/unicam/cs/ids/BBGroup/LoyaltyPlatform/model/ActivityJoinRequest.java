package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;


@Entity
@NoArgsConstructor
@Setter
@Getter
public class ActivityJoinRequest implements JoinRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_request_id", nullable = false)
    private Long activityRequestId;

    private boolean validated = false;
    @DateTimeFormat(pattern = "yyyy--MM--dd")
    private Date date;
    private String activityEmail;

    private String activityName;

    private String vatCode;

    private String address;

    private String phone;


    public ActivityJoinRequest(String activityEmail, String activityName, String vatCode, String address, String phone) {
        this.activityEmail = activityEmail;
        this.activityName = activityName;
        this.vatCode = vatCode;
        this.address = address;
        this.phone = phone;
        this.date = Calendar.getInstance().getTime();
    }

    public void validate(){this.validated=true;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityJoinRequest that = (ActivityJoinRequest) o;

        return getActivityEmail() != null ? getActivityEmail().equals(that.getActivityEmail()) : that.getActivityEmail() == null;
    }

    @Override
    public int hashCode() {
        return getActivityEmail() != null ? getActivityEmail().hashCode() : 0;
    }
}
