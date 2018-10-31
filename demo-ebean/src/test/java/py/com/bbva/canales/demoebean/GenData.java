package py.com.bbva.canales.demoebean;

import py.com.bbva.canales.demoebean.entity.Address;
import py.com.bbva.canales.demoebean.entity.Contact;
import py.com.bbva.canales.demoebean.entity.Customer;
import py.com.bbva.canales.demoebean.entity.Message;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;

public class GenData {
    public static void gen() {
        int phone = 971110798;
        int docNr = 3239727;
        int minusDays = 0;

        new Customer(
                "Derlis Zarate", ""+docNr++, Customer.Status.NEW,
                LocalDate.now().minus(Period.ofDays(minusDays++)),
                new Address("Mcal. Lopez y Terreani", "Luque"),
                Arrays.asList(
                        new Contact(""+ phone--, Contact.Type.PHONE,null),
                        new Contact(""+ phone--, Contact.Type.PHONE,null)
                ),
                Arrays.asList(
                        new Message("Los murciélagos me asustan. Es tiempo de que mis enemigos compartan mi temor. -Batman", new Date(), null),
                        new Message("Todos somos uno", new Date(),null)
                )
        ).save();

        new Customer(
                "Cristian Noguera", ""+docNr++, Customer.Status.NEW,
                LocalDate.now().minus(Period.ofDays(minusDays++)),
                new Address("Mcal. Lopez y Terreani", "San Lorenzo"),
                Arrays.asList(
                        new Contact(""+ phone--, Contact.Type.PHONE,null),
                        new Contact(""+ phone--, Contact.Type.PHONE,null)
                ),
                Arrays.asList(
                        new Message("Hulk smashes!",new Date(), null),
                        new Message("Mi secreto es que siempre estoy furioso. – Bruce Banner",new Date(), null)
                )
        ).save();

        new Customer(
                "Alicia Cañete", ""+docNr++, Customer.Status.NEW,
                LocalDate.now().minus(Period.ofDays(minusDays++)),
                new Address("Mcal. Lopez y Terreani", "Luque"),
                Arrays.asList(
                        new Contact(""+ phone--, Contact.Type.PHONE,null),
                        new Contact(""+ phone--, Contact.Type.PHONE,null)
                ),
                Arrays.asList(
                        new Message("Mei checking in.",new Date(), null),
                        new Message("Ice wall, coming up. -Alchemist",new Date(), null)
                )
        ).save();

        new Customer(
                "Claudia Barreto", ""+docNr++, Customer.Status.NEW,
                LocalDate.now().minus(Period.ofDays(minusDays++)),
                new Address("Mcal. Lopez y Terreani", "Fernando de la Mora"),
                Arrays.asList(
                        new Contact(""+ phone--, Contact.Type.PHONE,null),
                        new Contact(""+ phone--, Contact.Type.PHONE,null)
                ),
                Arrays.asList(
                        new Message("- Kimberly Hart",new Date(), null),
                        new Message("Morfosis Amigos! Iniciar Mórfosis! A metamorfosearse!", new Date(),null)
                )
        ).save();

        new Customer(
                "Rafael Estigarribia", ""+docNr++, Customer.Status.NEW,
                LocalDate.now().minus(Period.ofDays(minusDays++)),
                new Address("Mcal. Lopez y Terreani", "Asuncion"),
                Arrays.asList(
                        new Contact(""+ phone--, Contact.Type.PHONE,null),
                        new Contact(""+ phone--, Contact.Type.PHONE,null)
                ),
                Arrays.asList(
                        new Message("Yo lo habría hecho mejor. -Dr Strange", new Date(), null),
                        new Message("Dormamu, he venido para negociar. -Dr Strange", new Date(),null)
                )
        ).save();

        new Customer(
                "Choco", ""+docNr++, Customer.Status.MIGRATED,
                LocalDate.now().minus(Period.ofDays(minusDays++)),
                new Address("Mcal. Lopez y Terreani", "Asuncion"),
                Arrays.asList(
                        new Contact(""+ phone--, Contact.Type.PHONE,null),
                        new Contact(""+ phone--, Contact.Type.PHONE,null)
                ),
                Arrays.asList(
                        new Message("¡Capitaaaaaaaaaaaaaaaaaan cavernicola!", new Date(), null),
                        new Message("Eeeeeeee hijo -Cavernicolita", new Date(), null),
                        new Message("Onga ponga",new Date(), null)
                )
        ).save();

        new Customer(
                "David Hofmann", "3239726", Customer.Status.MIGRATED,
                LocalDate.now(),
                new Address("Mcal. Lopez y Terreani", "Asuncion"),
                Arrays.asList(
                        new Contact("0971110799", Contact.Type.PHONE,null),
                        new Contact("0982424984", Contact.Type.PHONE,null),
                        new Contact("hofmanndavid@gmail.com", Contact.Type.EMAIL,null)
                ),
                Arrays.asList(
                        new Message("Chewbacca Chewie", new Date(),null),
                        new Message("Eeeeehhhhh -Chewbacca", new Date(),null),
                        new Message("Aaaaahhhhh -Chewbacca",new Date(), null)
                )
        ).save();

        new Customer(
                "Vicente Franco", ""+docNr++, Customer.Status.NEW,
                LocalDate.now(),
                new Address("Mcal. Lopez y Terreani", "Asuncion"),
                Arrays.asList(
                        new Contact("vicenteramon.franco@bbva.com", Contact.Type.EMAIL,null)
                ),
                null
        ).save();
    }
}
