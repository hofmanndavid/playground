package io.hdavid.test.entity;

import io.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@Table(name = "appuser")
public class User extends BasicEbeanEntity {

    public enum Rol {
        ADMIN,
        DEVELOPER,
        OPERATOR;
    }

    private String username;
    private String password;
    private String roles;

    public Set<Rol> listRoles() {
        if (roles == null || roles.trim().isEmpty())
            return new HashSet<>();
        return Arrays.stream(roles.split(","))
                .map(s->Rol.valueOf(s))
                .collect(Collectors.toSet());

    }
    public void setRoles(Set<Rol> roleList) {
        this.roles = roleList.stream()
                .map(r -> r.name())
                .collect(Collectors.joining(","));
    }

    public boolean hasRol(Rol rol) {
        if (roles == null)
            return false;
        return roles.contains(rol.name());
    }

    public void addRol(Rol rol) {
        Set<Rol> rols = listRoles();
        rols.add(rol);
        setRoles(rols);
    }

}
