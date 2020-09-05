package ru.dev.interview.project.phonebook.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor
@Setter
@ToString
public class AddressType {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;
    private String value;
    @OneToMany(mappedBy = "type")
    private Set<Address> addresses = new HashSet<>();

    public AddressType(String value) {
        this.value = value;
    }
}
