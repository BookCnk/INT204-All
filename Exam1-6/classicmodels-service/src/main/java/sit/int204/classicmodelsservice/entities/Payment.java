package sit.int204.classicmodelsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    private String checkNumber;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customer customerNumber;
    private Date paymentDate;
    private Integer amount;
}
