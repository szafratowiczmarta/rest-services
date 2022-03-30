package src.exceptions;

public class BuildingNotFound extends RuntimeException {

    public BuildingNotFound(Long id) { super("Could not find building " + id); }
}