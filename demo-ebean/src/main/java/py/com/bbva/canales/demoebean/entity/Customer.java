package py.com.bbva.canales.demoebean.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Customer extends AbstractEntity {

    private String fullName;

    private String documentNumber;

}
