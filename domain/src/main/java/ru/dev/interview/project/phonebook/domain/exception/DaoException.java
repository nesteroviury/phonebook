package ru.dev.interview.project.phonebook.domain.exception;

public class DaoException extends Exception {
    public static final String ENTITY_NOT_FOUND = "Entity with id = %s not found";

    public DaoException(String message) {
        super(message);
    }
}
