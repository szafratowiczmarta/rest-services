package src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.exceptions.ApartmentNotFound;
import src.model.Apartment;
import src.model.ApartmentsRepository;
import src.model.Building;
import src.model.BuildingsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static src.GlobalConstants.MAX_FLAT_SIZE;

@RestController
public class ApartmentsController {

    @Autowired
    private final ApartmentsRepository apartmentsRepository;
    @Autowired
    private final BuildingsRepository buildingsRepository;

    public ApartmentsController(ApartmentsRepository apartmentsRepository,
                                BuildingsRepository buildingsRepository) {
        this.apartmentsRepository = apartmentsRepository;
        this.buildingsRepository = buildingsRepository;
    }

    @GetMapping("/apartments/free")
    List<Apartment> getFreeApartments() {
        return apartmentsRepository.findAll().stream()
                .filter(apartment -> apartment.isFree()).collect(Collectors.toList());
    }

    @GetMapping("/apartments")
    List<Apartment> getApartments(@RequestParam Optional<Double> maximumSize, @RequestParam Optional<Double> minimumSize) {
        List<Apartment> apartments = apartmentsRepository.findAll();
        return apartments.stream()
                .filter(apartment -> apartment.getSize() <= (maximumSize.isPresent() ? maximumSize.get() : MAX_FLAT_SIZE)
                        & apartment.getSize() >= (minimumSize.isPresent() ? minimumSize.get() : 0.0))
                .collect(Collectors.toList());
    }

    @GetMapping("/apartment/{id}")
    Apartment getApartmentById(@PathVariable Long id) {
        return apartmentsRepository.findById(id)
                .orElseThrow(() -> new ApartmentNotFound(id) );
    }

    @GetMapping("/apartment")
    List<Apartment> getApartmentByType(@RequestParam String type) {
        return apartmentsRepository.findByType(type);
    }

    @GetMapping("/building/{id}/apartments")
    List<Apartment> getBuildingApartmentsByBuildingId(@PathVariable Long id) {
        return apartmentsRepository.findAll().stream()
                .filter(apartment -> apartment.getBuildingId().equals(id)).collect(Collectors.toList());
    }

    @GetMapping("/building/apartments")
    List<Apartment> getBuildingApartmentsByBuildingName(@RequestParam String name) {
        List<Building> buildings = buildingsRepository.findByName(name);
        return !buildings.isEmpty() ? getBuildingApartmentsByBuildingId(buildings.get(0).getId()) : Collections.emptyList();
    }

    @PostMapping("/apartment")
    Apartment postApartment(@RequestBody Apartment apartment) {
        return apartmentsRepository.save(apartment);
    }

    @PutMapping("/apartment/{id}")
    Apartment replaceApartments(@RequestBody Apartment newApartment, @PathVariable Long id) {
        return apartmentsRepository.findById(id)
                .map(apartment -> {
                    if (newApartment.getName() != null) apartment.setName(newApartment.getName());
                    if (newApartment.getSize() != null) apartment.setSize(newApartment.getSize());
                    if (newApartment.getType() != null) apartment.setType(newApartment.getType());
                    apartment.setFree(newApartment.isFree());
                    return apartmentsRepository.save(apartment);
                })
                .orElseGet(() -> {
                    newApartment.setId(id);
                    return apartmentsRepository.save(newApartment);
                });
    }

    @DeleteMapping("/apartment/{id}")
    void deleteApartment(@PathVariable Long id) {
        apartmentsRepository.deleteById(id);
    }
}
