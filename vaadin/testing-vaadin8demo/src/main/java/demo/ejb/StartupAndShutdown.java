package demo.ejb;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DbMigrationConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import demo.entity.Usuario;
import lombok.SneakyThrows;
import org.avaje.dbmigration.MigrationConfig;
import org.avaje.dbmigration.MigrationRunner;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

//@Singleton
//@Startup
//@TransactionManagement(TransactionManagementType.BEAN)
public class StartupAndShutdown {

//	@EJB
	EntityAutoCreation eac;

	@Resource(mappedName = "java:jboss/datasources/demoDS")
	DataSource ds;

	volatile EbeanServer server;

	@PostConstruct
    @SneakyThrows
	private void atStartup() {


		MigrationConfig mc = new MigrationConfig();

		MigrationRunner mr = new MigrationRunner(mc);
		mr.run(ds.getConnection());

		ServerConfig config = new ServerConfig();


		config.setDataSource(ds);
		config.setName("db");
//		config.setUseJtaTransactionManager(true);
//        config.setAutoCommitMode(true);
		config.setDatabasePlatform(new PostgresPlatform());
		config.setRegister(true);
		config.setDefaultServer(true);
//        config.getAutoTuneConfig().setProfiling(true);
//        config.getAutoTuneConfig().setQueryTuning(true);
		//config.setCurrentUserProvider(new UserProvider());
		config.addPackage(Usuario.class.getPackage().getName());


		server = EbeanServerFactory.create(config); //	 used in multiple threads... no prob. trx are anaged in thread local storage.

		eac.atStartup();

	}


//	@Produces
//	@ApplicationScoped
	public EbeanServer obtainEbeanDefaultServer() {
		return server;
	}
}
