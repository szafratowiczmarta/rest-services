package src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.exceptions.BuildingNotFound;
import src.model.Building;
import src.model.BuildingsRepository;

import java.util.List;

@RestController
public class BuildingsController {

    @Autowired
    private final BuildingsRepository buildingsRepository;

    public BuildingsController(BuildingsRepository buildingsRepository) {
        this.buildingsRepository = buildingsRepository;
    }

    @GetMapping("/buildings")
    List<Building> getBuildings() {
        return buildingsRepository.findAll();
    }

    @GetMapping("/building/{id}")
    Building getBuildingById(@PathVariable Long id) {
        return buildingsRepository.findById(id)
                .orElseThrow(() -> new BuildingNotFound(id) );
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
    void deleteBuilding(@PathVariable Long id) {
        buildingsRepository.deleteById(id);
    }
    
}
