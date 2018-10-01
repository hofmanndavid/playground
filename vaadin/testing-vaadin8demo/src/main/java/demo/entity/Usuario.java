package demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Usuario {

    @Id
//    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String email;
    private String email2;

    @Column(nullable = false)
    private String password;

    private String fullName;
}
