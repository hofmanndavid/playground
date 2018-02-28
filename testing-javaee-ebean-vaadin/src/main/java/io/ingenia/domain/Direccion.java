package io.ingenia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Direccion {

    @Id
    private Long id;

    private String calle;

    private String nro;
    @ManyToOne(optional = false)
    private Persona persona;

    @ManyToOne(optional = false)
    private Ciudad ciudad;


}
