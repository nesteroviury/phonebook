package ru.dev.interview.project.phonebook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@ToString
@Setter
public class Person implements Serializable {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Contact contact;
    private Address address;
}
