package src.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "building")
public class Building {

    @Id
    @Column(name = "building_id")
    private Long id;
    private String name;
    private String address;
    private Double pricePerSquareMeter;
    private Double costPerSquereMeter;
    @OneToMany(mappedBy = "building")
    private List<Apartment> apartments = new ArrayList<>();

    public Building() {}

    public Building(Long id, String name, String address, Double pricePerSquareMeter, Double costPerSquereMeter, List<Apartment> apartments) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.pricePerSquareMeter = pricePerSquareMeter;
        this.costPerSquereMeter = costPerSquereMeter;
        this.apartments = apartments;
    }
}
