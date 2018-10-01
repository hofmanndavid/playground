package io.ingenia.crosscut;

import io.ebean.Ebean;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.config.dbplatform.postgres.PostgresPlatform;
import io.ebean.dbmigration.MigrationConfig;
import io.ebean.dbmigration.MigrationRunner;
import io.ingenia.domain.Usuario;
import io.ingenia.domain.query.QUsuario;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenceConfig {

    @Resource(mappedName = "java:/MecDS")
    DataSource ds;

    @PostConstruct
    @SneakyThrows
    public void configEbeanServer() {


        System.out.println("Init DB Migration");
        // run any pending migrations
        MigrationConfig mc = new MigrationConfig();
        MigrationRunner mr = new MigrationRunner(mc);
        mr.run(ds.getConnection());

        ServerConfig config = new ServerConfig();
        config.setDataSource(ds);
        config.setName("db");
        config.setAutoCommitMode(false);
        config.setDatabasePlatform(new PostgresPlatform());
        config.setRegister(true);
        config.setDefaultServer(true);
        config.setTransactionRollbackOnChecked(true);
        config.addPackage(Usuario.class.getPackage().getName());

        // used in multiple threads... no prob. trx are managed in a thread local storage.
        System.out.println("Init Ebean Server");
        EbeanServerFactory.create(config);
        System.out.println("Ebean Server created");


        Usuario usuario = new QUsuario().username.eq("admin").findUnique();
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setFullName("David Hofmann");
            usuario.setUsername("admin");
            usuario.setPassword("admin");
            usuario.setEmail("hofmanndavid@gmail.com");
            Ebean.save(usuario);
        }

//        if (new QImpresoras().findCount() == 0) {
//            Impresora bean = new Impresora();
//            bean.setId(-1L);
//            bean.setNombre("Prueba");
//            Ebean.save(bean);
//        }


//      TODO & FIXME research other configuration options...
//		config.setUseJtaTransactionManager(true);
//      config.getAutoTuneConfig().setProfiling(true);
//      config.getAutoTuneConfig().setQueryTuning(true);
//      config.setCurrentUserProvider(new UserProvider());


        // Usage example
//        List<Usuario> u = Ebean.execute(()-> {
//            return new QUsuario()
//                    .email.eq("")
//                    .findList();
//        });
    }

//    @Produces
//    public EbeanServer getDefaultServer() {
//        return server;
//    }

}
