package src.exceptions;

public class ApartmentNotFound extends RuntimeException {

    public ApartmentNotFound(Long id) {
        super("Could not find apartment " + id);
    }
}