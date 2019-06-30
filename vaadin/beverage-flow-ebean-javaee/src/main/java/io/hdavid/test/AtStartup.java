package io.hdavid.test;

import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.migration.MigrationConfig;
import io.ebean.migration.MigrationRunner;
import io.hdavid.test.entity.Customer;
import io.hdavid.test.entity.query.QCustomer;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;


@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class AtStartup {

    @Resource(mappedName = "java:jboss/datasources/EbeanTestDS")
    private DataSource ds;

    @Inject
    private UserTransaction ut;

    @SneakyThrows
    @PostConstruct
    public void startup() {
        new MigrationRunner(new MigrationConfig()).run(ds); // begin/commits transaction for the migration...

        ServerConfig config = new ServerConfig();
        config.setDataSource(ds);
        config.addPackage(Customer.class.getPackage().getName());
        config.setUseJtaTransactionManager(true); // false by default
        config.setAutoCommitMode(false);
        EbeanServerFactory.create(config);

//        ut.begin();

        System.out.println("finding customers");
        System.out.println(new QCustomer().findList());
        System.out.println("-----------------");

//        ut.commit();
    }
}
