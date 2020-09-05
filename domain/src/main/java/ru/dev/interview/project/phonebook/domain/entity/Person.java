package ru.dev.interview.project.phonebook.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor
@Setter
@ToString
public class Person {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", sequenceName = "PERSON_SEQ", allocationSize = 1)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "contact_id")
    private Contact contact;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "address_id")
    private Address address;

    @Builder
    public Person(String firstName, String middleName, String lastName, Contact contact, Address address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
    }
}
