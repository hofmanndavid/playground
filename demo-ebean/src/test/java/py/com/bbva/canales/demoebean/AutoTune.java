package py.com.bbva.canales.demoebean;

import io.ebean.DocumentStore;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import py.com.bbva.canales.demoebean.entity.Customer;
import py.com.bbva.canales.demoebean.entity.query.QCustomer;

import java.util.List;

@Slf4j
public class AutoTune {

    @Before
    public void loadData() {
//        GenData.gen();
//        rebuildIndex();
        ServerConfig sc = new ServerConfig();
        sc.loadFromProperties();
//        sc.getAutoTuneConfig().setProfiling(true);
        sc.getAutoTuneConfig().setQueryTuning(true);
        EbeanServerFactory.create(sc);
    }

    @Test
    public void testExecutionPaths() {
        log.info("Auto tune magic");

        List<Customer> asuncenos = todosLosQueEstanEnCiudad("asuncion");

        Customer.prettyPrint(asuncenos);

        List<Customer> migrados = todosLosMigrados();
        migrados.forEach(i->log.info(i.getName()));
//        Customer.prettyPrint(migrados);
    }

    public List<Customer> todosLosQueEstanEnCiudad(String ciudad) {
        List<Customer> list = new QCustomer()
                .billingAddress.city.icontains(ciudad)
                .findList();
        return list;
    }

    public List<Customer> todosLosMigrados() {
        List<Customer> list = new QCustomer()
                .status.eq(Customer.Status.MIGRATED)
                .findList();
        return list;
    }

}
