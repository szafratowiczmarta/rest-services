package src.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class EmptyResultException extends EmptyResultDataAccessException {

    public EmptyResultException(Integer id, String type) { super(String.format("%s with id %s not found", type, id), id.intValue()); }

}
