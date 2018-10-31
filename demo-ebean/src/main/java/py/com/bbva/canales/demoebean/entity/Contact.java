package py.com.bbva.canales.demoebean.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString(onlyExplicitlyIncluded = true)
public class Contact extends AbstractEntity {

    @ToString.Include
    private String contact;

    @Enumerated(EnumType.STRING)
    @ToString.Include
    private Type type;

    @ManyToOne
    private Customer customer;

    public enum Type {
        PHONE, EMAIL
    }
}
