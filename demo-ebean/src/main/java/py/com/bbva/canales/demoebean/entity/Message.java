package py.com.bbva.canales.demoebean.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

// "Message" is a reserved word
@Entity
@Getter
@Setter
public class Message extends AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha; // crm fecha

    private Boolean sentByClient; // crm enviadopormi

    @Column(length = 10000)
    private String message; // crm mensaje

    @Column(length = 10000)
    private String aditional; // crm detallemensaje

    @Column(length = 256)
    private String opName; // crm nombreoperador // estatico

    @ManyToOne
    private Interaction interaction;


    @PrePersist
    public void loadDataToCrm() {
//        if (!getSentByClient())
//            WhatsApp.sendMessage(getInteraction().getIdCanal(), getMessage());
//
//        Crm.logToCrmAsync(interaction.getId()+"",
//                getMessage(),
//                interaction.getCustomerDocumentNumber(),
//                getAditional(),
//                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(getFecha()),
//                getSentByClient()? "true" : "false",
//                interaction.getCanal().getCode(),
//                getOpName(),
//                interaction.getCustomerName());
    }

}
