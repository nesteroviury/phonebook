package ru.dev.interview.project.phonebook.rest.controller.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Url {
    public static final String ADD_PERSON = "add";
    public static final String API = "phone-book/api/";
    public static final String DELETE = "delete";
    public static final String PERSON_LIST = "list";
    public static final String SEARCH = "search/{criteria}";
}
