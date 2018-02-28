package io.ingenia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
public class Persona {

    @Id
    private Long id;

    private String nombre;

    private String apellido;

    @OneToMany(mappedBy = "persona")
    private List<Direccion> direcciones;

}
