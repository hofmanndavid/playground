package io.ingenia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Ciudad {

    @Id
    private Long id;

    private String nombre;

}
