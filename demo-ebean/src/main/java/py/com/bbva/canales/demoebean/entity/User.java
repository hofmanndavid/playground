package py.com.bbva.canales.demoebean.entity;

import io.ebean.annotation.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

// "User" is a reserved word in some SQL implementations
@Entity(name = "UserInfo")
@Getter
@Setter
public class User extends AbstractEntity {

    @NotNull
    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String roles;

}
