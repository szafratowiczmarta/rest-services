package src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.exceptions.BuildingNotFound;
import src.model.ApartmentsRepository;
import src.model.Building;
import src.model.BuildingsRepository;

import java.util.List;

@RestController
public class BuildingsController {

    @Autowired
    private final BuildingsRepository buildingsRepository;
    @Autowired
    private final ApartmentsRepository apartmentsRepository;

    BuildingsController(BuildingsRepository buildingsRepository, ApartmentsRepository apartmentsRepository) {
        this.buildingsRepository = buildingsRepository;
        this.apartmentsRepository = apartmentsRepository;
    }

    @GetMapping("/buildings")
    List<Building> getBuildings() {
        return buildingsRepository.findAll();
    }

    @PostMapping("/buildings")
    Building postBuilding(@RequestBody Building building) {
        /*if (!building.getApartaments().isEmpty() && building.getApartaments() != null) {
            building.getApartaments().stream()
                    .map(a -> {
                        Apartment apartment = new Apartment(a.getName(), a.getType(),a.getSize(),a.isFree());
                        return apartmentsRepository.save(apartment);
                    });
        }*/
        //Building b = new Building(building.getName(), building.getAddress(), building.getPricePerSquareMeter(), building.getCostPerSquereMeter());
        return buildingsRepository.save(building);
    }

    @GetMapping("/buildings/{id}")
    Building getBuildingById(@PathVariable Long id) {
        return buildingsRepository.findById(id)
                .orElseThrow(() -> new BuildingNotFound(id) );
    }

    @PutMapping("/buildings/{id}")
    Building replaceBuilding(@RequestBody Building newBuilding, @PathVariable Long id) {
        return buildingsRepository.findById(id)
                .map(building -> {
                    if (newBuilding.getName() != null) building.setName(newBuilding.getName());
                    if (newBuilding.getAddress() != null) building.setAddress(newBuilding.getAddress());
                    if (newBuilding.getCostPerSquereMeter() != null) building.setCostPerSquereMeter(newBuilding.getCostPerSquereMeter());
                    if (newBuilding.getPricePerSquareMeter() != null) building.setPricePerSquareMeter(newBuilding.getPricePerSquareMeter());

                    return buildingsRepository.save(building);
                })
                .orElseGet(() -> {
                    newBuilding.setId(id);
                    return buildingsRepository.save(newBuilding);
                });
    }

    @DeleteMapping("/buildings/{id}")
    void deleteBuilding(@PathVariable Long id) {
        buildingsRepository.deleteById(id);
    }
}