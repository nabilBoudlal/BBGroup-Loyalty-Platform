package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

/*
 * This class represent an activity's join request, which is also an entity in the db
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class ActivityJoinRequest implements JoinRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activityGenerator")
    @SequenceGenerator(name = "activityGenerator", allocationSize = 1)
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


    public ActivityJoinRequest(String activityName, String address,String activityEmail, String phone, String vatCode) {
        this.activityName = activityName;
        this.address = address;
        this.activityEmail = activityEmail;
        this.phone = phone;
        this.vatCode = vatCode;
        this.date = Calendar.getInstance().getTime();
    }

    public void validate(){this.validated=true;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityJoinRequest that = (ActivityJoinRequest) o;

        if (getActivityRequestId() != null ? !getActivityRequestId().equals(that.getActivityRequestId()) : that.getActivityRequestId() != null)
            return false;
        if (getActivityEmail() != null ? !getActivityEmail().equals(that.getActivityEmail()) : that.getActivityEmail() != null)
            return false;
        return getPhone() != null ? getPhone().equals(that.getPhone()) : that.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getActivityRequestId() != null ? getActivityRequestId().hashCode() : 0;
        result = 31 * result + (getActivityEmail() != null ? getActivityEmail().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }
}
