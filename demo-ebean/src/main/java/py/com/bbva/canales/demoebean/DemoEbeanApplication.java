package py.com.bbva.canales.demoebean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.ebean.EbeanServerFactory;
import io.ebean.FutureList;
import io.ebean.config.AutoTuneConfig;
import io.ebean.config.DocStoreConfig;
import io.ebean.config.ServerConfig;
import py.com.bbva.canales.demoebean.entity.Customer;
//import py.com.bbva.canales.demoebean.entity.query.QCustomer;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DemoEbeanApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {


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

		ebeanConfig.setDataSource(hikariDataSource);
		ebeanConfig.setDefaultServer(true);
		AutoTuneConfig autotune = new AutoTuneConfig();
		autotune.setProfiling(true);
		autotune.setQueryTuning(true);
		ebeanConfig.setAutoTuneConfig(autotune);

		DocStoreConfig docstore = new DocStoreConfig();
		docstore.setUrl("http://127.0.0.1:9201");
		docstore.setActive(true);
		docstore.setGenerateMapping(true);
		docstore.setDropCreate(true);

		ebeanConfig.setDocStoreConfig(docstore);
		EbeanServerFactory.create(ebeanConfig);

//		System.out.println(userList.get(0).getName());
//
//		if (user == null) {
//			user = new User();
//			user.setName("Operador 1");
//			user.setEmail("operador1");
//			user.setPassword("asdf");
//			user.setRoles("admin");
//			user.save();
//		}
//
//
//
//
//		System.out.println(new QUser().email.eq("operador1").findOne());

//
//		LocalDate lastWeek = LocalDate.now().minus(Period.ofDays(7));
//
//		FutureList<Customer> futureList = new QCustomer()
//				.name.istartsWith("rob")
//				.findFutureList();
//
//		if (!futureList.isDone())
//			futureList.cancel(true);
//
//		List<Customer> list = futureList.get();
//
//		// or with timeout
//		List<Customer> list2 = futureList.get(30, TimeUnit.SECONDS);




//
//		int count = new QCustomer()
//				.name.istartsWith("rob")
//				.billingAddress.city.equalTo("Asuncion")
//				.status.eq(Customer.Status.NEW)
//				.registered.before(lastWeek)
//				.setMaxRows(10)
//				.orderBy()
//				.name.asc()
//				.id.desc()
//				.findCount();
//
//		PagedList<Customer> pagedList = new QCustomer()
//				.name.istartsWith("rob")
//				.billingAddress.city.equalTo("Asuncion")
//				.status.eq(Customer.Status.NEW)
//				.registered.before(lastWeek)
//				.orderBy()
//				.name.asc()
//				.setFirstRow(0)
//				.setMaxRows(10) // page size
//				.findf
//
//		pagedList.loadCount(); // fetch in background thread the count
//
//		int 		   pageSize       = pagedList.getPageSize(); // same as in setMaxRows(...)
//		int 		   pageIndex      = pagedList.getPageIndex();
//		int 		   totalPageCount = pagedList.getTotalPageCount();
//		int 		   totalCount     = pagedList.getTotalCount();
//		boolean 	   hasNext        = pagedList.hasNext();
//		boolean 	   hasPrev        = pagedList.hasPrev();
//		List<Customer> list           = pagedList.getList();
//
//		String.format("",pageSize,
//				pageIndex,
//				totalPageCount,
//				totalCount,
//				hasNext,
//				hasPrev,
//				list           );

	}
}
