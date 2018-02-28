package demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Cliente {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Categoria categoria;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String nombre;
    private String apellido;
    private String ruc;

}
