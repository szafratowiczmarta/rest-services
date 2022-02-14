package src.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "size")
    private Double size;
    @Column(name = "is_free")
    private boolean isFree;
    @ManyToOne
    @JoinColumn(name = "building_building_id")
    private Building building;

    public Apartment() {}

    public Apartment(String name, String type, Double size, boolean isFree, Building building) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.isFree = isFree;
        this.building = building;
    }
}
