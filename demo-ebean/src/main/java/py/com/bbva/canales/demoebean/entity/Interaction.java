package py.com.bbva.canales.demoebean.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Interaction extends AbstractEntity {

//    @AllArgsConstructor
//    @Getter
//    public enum Canal {
//        SMS				("100000000"),
//        CORREO			("100000001"),
//        WHATSAPP		("100000002"),
//        FACEBOOK		("100000003"),
//        TWITTER			("100000004"),
//        CHAT_PERSONAL	("100000005"),
//        CHAT_INTERNO	("100000006"),
//        TELEFONO		("100000007"),
//        OTROS			("100000008");
//        private String code;
//    }
//
//    public enum Estado {
//        FINALIZADO,
//        ENCOLADO,
//        EN_CURSO,
//    }

    @Column(length = 256)
    private String opName; // crm nombreoperador // dinamico

    // ID used as crm nombre (which is the actual id of the interaction)
//    @Enumerated(EnumType.STRING)
//    private Estado state;

    @Column(length = 256)
    private String customerName; // crm nombrecontacto

    @Column(length = 256)
    private String customerDocumentNumber; // crm documentoReceptor

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(length = 256)
    private String idCanal;

//    @Enumerated(EnumType.STRING)
//    private Canal canal;

//    @OneToMany(mappedBy = "interaction")
//    private List<Message> messages;
//
//    public List<Message> getMessages() {
//        if (messages == null)
//            messages = new ArrayList<>();
//        return messages;
//    }



}
