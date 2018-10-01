package io.ingenia.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    private Long id;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    private String email;

    @Column(nullable = false)
    private String password;

    // false by default
    private boolean roleAdmin;
    private boolean roleImpresor;
    private boolean roleConsulta;

}
