package ru.dev.interview.project.phonebook.core.exception;

public class ServiceException extends Exception {
    public static final String ENTITY_NOT_FOUND = "Entity with id = %s not found";

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
