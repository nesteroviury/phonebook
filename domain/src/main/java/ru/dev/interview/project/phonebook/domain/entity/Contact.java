package ru.dev.interview.project.phonebook.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor
@Setter
@ToString
public class Contact {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    private String value;
    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private ContactType type;
    @OneToOne(mappedBy = "contact")
    private Person person;

    public Contact(String value, ContactType type) {
        this.value = value;
        this.type = type;
    }
}
