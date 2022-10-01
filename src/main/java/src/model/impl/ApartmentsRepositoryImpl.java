package src.model.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import src.model.ApartmentsRepository;

@AllArgsConstructor
public abstract class ApartmentsRepositoryImpl implements ApartmentsRepository {

    @Autowired
    private final ApartmentsRepository apartmentsRepository;

}
