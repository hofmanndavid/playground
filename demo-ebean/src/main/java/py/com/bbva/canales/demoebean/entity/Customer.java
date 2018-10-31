package py.com.bbva.canales.demoebean.entity;

import io.ebean.annotation.DocEmbedded;
import io.ebean.annotation.DocStore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@DocStore
@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString(onlyExplicitlyIncluded = true)
@Slf4j
public class Customer extends AbstractEntity {



    public enum Status { NEW, MIGRATED }

    @ToString.Include
    private String name;

    @ToString.Include
    private String docNumber;

    @ToString.Include
    private Status status;

    @ToString.Include
    private LocalDate registered;

    @DocEmbedded(doc = "*")
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Address billingAddress;

    @DocEmbedded(doc = "contact,type")
    @OneToMany(mappedBy="customer", cascade = CascadeType.PERSIST)
    private List<Contact> contacts;

    @DocEmbedded(doc = "fecha,message")
    @OneToMany(mappedBy="customer", cascade = CascadeType.PERSIST)
    private List<Message> messages;

    public static void prettyPrint(List<Customer> list) {
        StringBuilder sb = new StringBuilder(" - Found ").append(list.size()).append(" elements\n");
        list.forEach(i->{
            sb.append("\n"+i.toString());
            sb.append("\n\t"+i.getBillingAddress().toString());
            i.getContacts().forEach(c->sb.append("\n\t"+c.toString()));
            i.getMessages().forEach(c->sb.append("\n\t"+c.toString()));
        });

        log.info(sb.toString());
    }

}
