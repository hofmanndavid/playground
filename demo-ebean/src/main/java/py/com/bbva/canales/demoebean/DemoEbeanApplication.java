package py.com.bbva.canales.demoebean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import py.com.bbva.canales.demoebean.entity.User;
import py.com.bbva.canales.demoebean.entity.query.QUser;

public class DemoEbeanApplication {

	public static void main(String[] args) {


		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl("jdbc:h2:tcp://localhost/~/ebeandb");
//		hikariConfig.setJdbcUrl("jdbc:h2:~/ebeandb");
		hikariConfig.setUsername("sa");
		hikariConfig.setPassword("");
		hikariConfig.setAutoCommit(true);
		hikariConfig.setMinimumIdle(1);
		hikariConfig.setMaximumPoolSize(2);
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);


		ServerConfig ebeanConfig = new ServerConfig();
//		ebeanConfig.setRunMigration(true);
//		ebeanConfig.runDbMigration(hikariDataSource); // execute migration on this DS

		ebeanConfig.setDataSource(hikariDataSource);
		ebeanConfig.setDefaultServer(true);
		EbeanServerFactory.create(ebeanConfig);

		User user = new QUser().id.eq(1L).findOne(); //new QUser().email.eq("operador1").findOne();

		if (user == null) {
			user = new User();
			user.setName("Operador 1");
			user.setEmail("operador1");
			user.setPassword("asdf");
			user.setRoles("admin");
			user.save();
		}




		System.out.println(new QUser().email.eq("operador1").findOne());
















	}
}
