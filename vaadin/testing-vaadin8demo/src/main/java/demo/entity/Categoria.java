package demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private String descripcion;
}
