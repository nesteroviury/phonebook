package ru.dev.interview.project.phonebook.dto;

import lombok.Builder;
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

    @Builder
    public Person(Long id, String firstName, String middleName, String lastName, Contact contact, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
    }
}
