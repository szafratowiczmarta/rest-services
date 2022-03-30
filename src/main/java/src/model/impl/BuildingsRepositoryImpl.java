package src.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import src.model.Building;
import src.model.BuildingsRepository;

import java.util.stream.Collectors;

public abstract class BuildingsRepositoryImpl implements BuildingsRepository {

    @Autowired
    private final BuildingsRepository buildingsRepository;

    BuildingsRepositoryImpl(BuildingsRepository buildingsRepository) {
        this.buildingsRepository = buildingsRepository;
    }

    @Override
    public Building getBuildingByName(String name) {
        return buildingsRepository.findAll().stream()
                .filter(b -> b.getName().equals(name)).collect(Collectors.toList()).get(0);
    }
}
