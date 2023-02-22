package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Objects;
/*
 * This class represent an employee's join request, which is also an entity in the db
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmployeeJoinRequest implements JoinRequest{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeGenerator")
    @SequenceGenerator(name = "employeeGenerator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean validated = false;
    @DateTimeFormat(pattern = "yyyy--MM--dd")
    private Date date;
    private String employeeEmail;

    private String employeeName;

    private String employeeSurname;

    private String address;

    private String phone;


    public EmployeeJoinRequest( String employeeName,String employeeSurname, String address,String employeeEmail,  String phone) {
        this.employeeEmail = employeeEmail;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.address = address;
        this.phone = phone;
        this.date.toLocalDate();
    }


    @Override
    public void validate(){this.validated=true;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeJoinRequest that = (EmployeeJoinRequest) o;
        return Objects.equals(date, that.date) && Objects.equals(employeeEmail, that.employeeEmail) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, employeeEmail, phone);
    }


}
