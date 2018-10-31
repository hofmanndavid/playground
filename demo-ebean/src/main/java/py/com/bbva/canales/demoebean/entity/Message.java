package py.com.bbva.canales.demoebean.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString(onlyExplicitlyIncluded = true)
public class Message extends AbstractEntity {

    @ToString.Include
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @ToString.Include
    private Date fecha;

    @ManyToOne
    private Customer customer;
}
