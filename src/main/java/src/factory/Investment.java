package src.factory;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "enddate")
    private LocalDate endDate;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "number_of_apartments")
    private int numberOfApartments;

}

