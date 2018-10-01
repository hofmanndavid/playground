package demo.ejb.servlet;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import com.avaje.ebean.dbmigration.DbMigration;
import com.sun.corba.se.spi.activation.Server;
import demo.entity.Usuario;
import org.postgresql.ds.PGPoolingDataSource;

import java.io.IOException;
import java.sql.Driver;
import java.util.Date;

public class DbManualMigration {

    /**
     * Generate the next "DB schema DIFF" migration.
     * <p>
     * These migration are typically run using FlywayDB, Liquibase
     * or EbeanInit's own built in migration runner.
     * </p>
//     */
    public static void main(String[] args) throws IOException {

        // optionally specify the version and name
//        System.setProperty("ddl.migration.version", "1.0");
        System.setProperty("ddl.migration.name", "Next Iteration "+ new Date());

        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("A Data Source");
        source.setServerName("localhost");
        source.setDatabaseName("vaadindemo");
        source.setUser("vaadindemo");
        source.setPassword("vaadindemo");
        source.setMaxConnections(3);

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

    public void generate() {

        System.setProperty("ddl.migration.generate", "true");

        System.setProperty("ddl.migration.version", "1.1");
        System.setProperty("ddl.migration.name", "support end dating");

        // migration will be generated when EbeanServer instance starts
        // ... typically by running your application.
        Ebean.getDefaultServer();
    }
}