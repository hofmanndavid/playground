package py.com.bbva.canales.demoebean;

import io.ebean.DocumentStore;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.search.MultiMatch;
import io.ebean.search.TextSimple;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import py.com.bbva.canales.demoebean.entity.Address;
import py.com.bbva.canales.demoebean.entity.Contact;
import py.com.bbva.canales.demoebean.entity.Customer;
import py.com.bbva.canales.demoebean.entity.Message;
import py.com.bbva.canales.demoebean.entity.query.QCustomer;

import java.util.List;

@Slf4j
public class Elasticsearch {

    @Before
    public void loadData() {
//        GenData.gen();
        rebuildIndex();
    }

    @Test
    public void simple() {
        log.info("Searching Elasticsearch");
        List<Customer> list = new QCustomer()
                .setUseDocStore(true)
                .messages.message.match("batman")
//                .billingAddress.city.match("asuncion")
//                .multiMatch("Hulk",
//                        MultiMatch.fields("name","billingAddress.city","messages.message")
//                                .type(MultiMatch.Type.CROSS_FIELDS)
//                )
                .findList();
        log.info("Result From Elasticsearch {} items found", list.size());
        Customer.prettyPrint(list);
    }

    @Test
    public void dbOrElastic() {
        log.info("Searching Elasticsearch");
        List<Customer> list = new QCustomer()
                .messages.message.ilike("%batman%")
                .setUseDocStore(true)
                .findList();
        log.info("Result From Elasticsearch {} items found", list.size());
        Customer.prettyPrint(list);
    }

    @Test
    @SneakyThrows
    public void rebuildIndex() {
        log.info("Rebuilding Index");

        System.setProperty("ebean.docstore.generateMapping", "true");
        System.setProperty("ebean.docstore.dropCreate", "true");

        EbeanServer server = Ebean.getDefaultServer();
        DocumentStore documentStore = server.docStore();
        documentStore.indexAll(Customer.class);
//        documentStore.indexAll(Address.class);
//        documentStore.indexAll(Contact.class);
//        documentStore.indexAll(Message.class);

        Thread.currentThread().sleep(7000);
//    Map<String,Object> settings = new LinkedHashMap<>();
//    settings.put("refresh_interval", "-1");
//    documentStore.indexSettings("order", settings);
//
//    settings.put("refresh_interval", "1s");
//    documentStore.indexSettings("order", settings);

    }
}
