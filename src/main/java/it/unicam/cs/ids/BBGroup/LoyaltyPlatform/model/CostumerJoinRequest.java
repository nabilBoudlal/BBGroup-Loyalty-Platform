package it.unicam.cs.ids.BBGroup.LoyaltyPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CostumerJoinRequest implements JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean validated = false;
    @DateTimeFormat(pattern = "yyyy--MM--dd")
    private Date date;
    private String costumerEmail;

    private String costumerName;

    private String costumerSurname;

    private String address;

    private String phone;


    public CostumerJoinRequest(String costumerEmail, String costumerName, String costumerSurname, String address, String phone) {
        this.costumerEmail = costumerEmail;
        this.costumerName = costumerName;
        this.costumerSurname = costumerSurname;
        this.address = address;
        this.phone = phone;
        this.date.toLocalDate();
    }

    public void validate(){this.validated=true;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostumerJoinRequest that = (CostumerJoinRequest) o;
        return Objects.equals(date, that.date) && Objects.equals(costumerEmail, that.costumerEmail) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, costumerEmail, phone);
    }
}
