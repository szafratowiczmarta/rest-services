package src.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import src.model.ApartmentsRepository;
import src.model.BuildingsRepository;

public abstract class BuildingsRepositoryImpl implements BuildingsRepository {

    @Autowired
    private final BuildingsRepository buildingsRepository;
    @Autowired
    private final ApartmentsRepository apartmentsRepository;

    BuildingsRepositoryImpl(BuildingsRepository buildingsRepository,
                            ApartmentsRepository apartmentsRepository) {
        this.buildingsRepository = buildingsRepository;
        this.apartmentsRepository = apartmentsRepository;
    }

}
