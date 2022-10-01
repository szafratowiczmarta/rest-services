package src.model.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import src.model.ApartmentsRepository;
import src.model.BuildingsRepository;

@AllArgsConstructor
public abstract class BuildingsRepositoryImpl implements BuildingsRepository {

    @Autowired
    private final BuildingsRepository buildingsRepository;
    @Autowired
    private final ApartmentsRepository apartmentsRepository;

}
