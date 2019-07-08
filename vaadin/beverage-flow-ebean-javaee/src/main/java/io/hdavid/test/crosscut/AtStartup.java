package io.hdavid.test.crosscut;

import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.migration.MigrationConfig;
import io.ebean.migration.MigrationRunner;
import io.hdavid.test.entity.User;
import io.hdavid.test.entity.query.QUser;
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
        config.addPackage(User.class.getPackage().getName());
        config.setUseJtaTransactionManager(true); // false by default
        config.setAutoCommitMode(false);
        EbeanServerFactory.create(config);

        ut.begin();

        if (!new QUser().username.eq("admin").exists()) {
            User u = new User();
            u.setUsername("admin");
            u.setPassword("asdf");
            u.addRol(User.Rol.ADMIN);
            u.save();
        }

        ut.commit();
    }
}
