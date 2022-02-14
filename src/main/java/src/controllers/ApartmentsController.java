package src.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.exceptions.ApartmentNotFound;
import src.model.Apartment;
import src.model.ApartmentsRepository;

import java.util.List;

@RestController
public class ApartmentsController {

    @Autowired
    private final ApartmentsRepository apartmentsRepository;

    ApartmentsController(ApartmentsRepository apartmentsRepository) {
        this.apartmentsRepository = apartmentsRepository;
    }

    @GetMapping("/apartments")
    List<Apartment> getApartments() {
        return apartmentsRepository.findAll();
    }

    @PostMapping("/apartments")
    Apartment postApartment(@RequestBody Apartment apartment) {
        return apartmentsRepository.save(apartment);
    }

    @GetMapping("/apartments/{id}")
    Apartment getApartmentById(@PathVariable Long id) {
        return apartmentsRepository.findById(id)
                .orElseThrow(() -> new ApartmentNotFound(id) );
    }

    @PutMapping("/apartments/{id}")
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

    @DeleteMapping("/apartments/{id}")
    void deleteApartment(@PathVariable Long id) {
        apartmentsRepository.deleteById(id);
    }
}
