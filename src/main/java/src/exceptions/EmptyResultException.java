package src.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class EmptyResultException extends EmptyResultDataAccessException {

    public EmptyResultException(Long id, String type) { super(String.format("%s with id %s not found", type, id), id.intValue()); }

}
