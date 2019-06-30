package io.hdavid.test.entity;

import io.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Customer extends Model {

    @Id
    private Long id;

    private String name;
}
