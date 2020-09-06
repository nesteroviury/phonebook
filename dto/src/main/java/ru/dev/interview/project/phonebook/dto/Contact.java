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
public class Contact implements Serializable {
    private static final long serialVersionUID = 7966625570665946676L;
    private Long id;
    private String value;
}
