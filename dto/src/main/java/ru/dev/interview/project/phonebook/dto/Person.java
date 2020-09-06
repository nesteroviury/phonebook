package ru.dev.interview.project.phonebook.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@Setter
public class Person implements Serializable {
    private static final long serialVersionUID = 5804773725227706345L;
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Contact contact;
    private Address address;

    public Person() {
        String defaultValue = "";
        firstName = defaultValue;
        middleName = defaultValue;
        lastName = defaultValue;
        Contact contact = new Contact();
        contact.setValue(defaultValue);
        this.contact = contact;
        Address address = new Address();
        address.setValue(defaultValue);
        this.address = address;
    }
}
