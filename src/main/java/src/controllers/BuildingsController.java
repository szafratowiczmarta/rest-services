package src.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import src.exceptions.EmptyResultException;
import src.model.Building;
import src.model.BuildingsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BuildingsController {

    @Autowired
    private final BuildingsRepository buildingsRepository;

    @GetMapping("/buildings")
    List<Building> getBuildings() {
        return buildingsRepository.findAll();
    }

    @GetMapping("/building/{id}")
    Optional<Building> getBuildingById(@PathVariable Long id) {
        return buildingsRepository.findById(id);
    }

    @GetMapping("/building")
    List<Building> getBuildingByName(@RequestParam String name) {
        return buildingsRepository.findByName(name);
    }

    @PostMapping("/building")
    Building postBuilding(@RequestBody Building building) {
        return buildingsRepository.save(building);
    }

    @PutMapping("/building/{id}")
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

    @DeleteMapping("/building/{id}")
    String deleteBuilding(@PathVariable Long id) {
        try {
            buildingsRepository.deleteById(id);
            return String.format("Building %s deleted", id);
        } catch(EmptyResultDataAccessException e) {
            return new EmptyResultException(id, "Building").getMessage();
        }
    }
    
}
