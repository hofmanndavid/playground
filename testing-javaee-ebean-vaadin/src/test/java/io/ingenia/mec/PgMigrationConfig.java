package io.ingenia.mec;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.config.dbplatform.postgres.PostgresPlatform;
import io.ebean.dbmigration.DbMigration;
import io.ingenia.domain.Usuario;
import org.postgresql.ds.PGPoolingDataSource;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PgMigrationConfig {

    public static void main(String[] args) throws IOException {

//        System.setProperty("ddl.migration.version", "1.0");// optional & auto-increment
        String messageIteration = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.setProperty("ddl.migration.name", "Next Iteration "+ messageIteration);

        PGPoolingDataSource source = new PGPoolingDataSource();
//        source.setDataSourceName("");
        source.setServerName("localhost");
        source.setDatabaseName("vtech");
        source.setUser("vtech");
        source.setPassword("vtech");
        source.setMaxConnections(1);

        ServerConfig sc = new ServerConfig();
        sc.setDatabasePlatform(new PostgresPlatform());
        sc.setName("db");
        sc.setRegister(true);
        sc.setDefaultServer(true);
//        sc.setDdlGenerate(true);
        sc.setDataSource(source);
        sc.addPackage(Usuario.class.getPackage().getName());
        EbeanServer es = EbeanServerFactory.create(sc); //	 used in multiple threads... no prob. trx are anaged in thread local storage.

        DbMigration dbMigration = new DbMigration();
        dbMigration.setPlatform(new PostgresPlatform());
        dbMigration.setServerConfig(sc);

        // generate the migration ddl and xml
        // ... with EbeanServer in "offline" mode
        dbMigration.generateMigration();
    }

}
