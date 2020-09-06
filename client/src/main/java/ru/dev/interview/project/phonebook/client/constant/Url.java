package ru.dev.interview.project.phonebook.client.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Url {
    private static final String ADD_PERSON = "add";
    public static final String DELETE = "delete";
    private static final String PERSON_LIST = "list";
    private static final String SEARCH = "search";

    public static final String APP = "phone-book/";
    public static final String APP_ADD_PERSON = APP + ADD_PERSON;
    public static final String APP_DELETE_PERSON = APP + DELETE;
    public static final String APP_PERSON_LIST = APP + PERSON_LIST;
    public static final String APP_SEARCH = APP + SEARCH;

    public static final String REST_API = "http://localhost:8080/phone-book/api/";
    public static final String REST_ADD_PERSON = REST_API.concat(ADD_PERSON);
    public static final String REST_DELETE_PERSON = REST_API.concat(DELETE);
    public static final String REST_PERSON_LIST = REST_API.concat(PERSON_LIST);
    public static final String REST_SEARCH = REST_API.concat(SEARCH);
}
