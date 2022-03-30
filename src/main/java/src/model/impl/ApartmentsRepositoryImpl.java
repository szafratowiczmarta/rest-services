package src.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import src.model.ApartmentsRepository;

public abstract class ApartmentsRepositoryImpl implements ApartmentsRepository {

    @Autowired
    private final ApartmentsRepository apartmentsRepository;

    ApartmentsRepositoryImpl(ApartmentsRepository apartmentsRepository) {
        this.apartmentsRepository = apartmentsRepository;
    }

}
