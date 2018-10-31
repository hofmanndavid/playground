package py.com.bbva.canales.demoebean.entity;

import lombok.*;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString(onlyExplicitlyIncluded = true)
public class Address extends AbstractEntity {

    @ToString.Include
    private String address;

    @ToString.Include
    private String city;

}
