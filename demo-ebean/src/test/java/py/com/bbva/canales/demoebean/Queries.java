package py.com.bbva.canales.demoebean;

import io.ebean.ExpressionList;
import io.ebean.PagedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import py.com.bbva.canales.demoebean.entity.Address;
import py.com.bbva.canales.demoebean.entity.Customer;
import py.com.bbva.canales.demoebean.entity.query.QAddress;
import py.com.bbva.canales.demoebean.entity.query.QCustomer;
import py.com.bbva.canales.demoebean.entity.query.QMessage;

import java.util.List;

@Slf4j
public class Queries {

    @Before
    public void loadData() {
//        GenData.gen();
    }

    @Test
    public void simpleQUeries() {
        System.out.println("simple Queries");
        List<Customer> list = new QCustomer()
                .messages.message.icontains("eh")
//                .messages.isEmpty()
//                .id.greaterThan(6L)
//                .name.icontains("rafa")
//                .orderBy()
//                    .name.asc()
//                    .id.desc()
                .findList();

        Customer.prettyPrint(list);
    }

    @Test
    public void andOrQUeries() {
        System.out.println("simple Queries");
        List<Customer> list = new QCustomer()
                .messages.isNotEmpty()
                .or()
                    .id.lessThan(1L)
                    .and()
                        .name.icontains("derlis")
                        .billingAddress.city.icontains("luque")
                    .endAnd()
                .endOr()
                .findList();

        Customer.prettyPrint(list);
    }

    @Test
    public void selectAndFetch() {
        System.out.println("simple Queries");
        QCustomer cust = QCustomer.alias();
        QMessage mess = QMessage.alias();
        List<Customer> list = new QCustomer()
                .select(cust.id,cust.name)
                .messages.fetch(mess.id, mess.fecha)
                .contacts.fetchLazy()
                .billingAddress.fetchLazy()
                .findList();
        list.forEach(i-> {
            log.info("Customer Name: {}", i.getName());
            i.getMessages().forEach(m->log.info("\t Msg recibido en fecha: {}", m.getFecha()));
        });

        log.info("Lo sigte dispara lazy loading");

        list.forEach(i-> {
            log.info("Customer Name: {}", i.getName());
            i.getMessages().forEach(m->log.info("\t Msg recibido: {}", m.getMessage()));
        });

//        Customer.prettyPrint(list);
    }


    @Test
    public void pagedQueries() {
        System.out.println("simple Queries");
        PagedList<Customer> pagedList = new QCustomer()
                .id.greaterThan(1L)
                .setMaxRows(2)
                .setFirstRow(0)
                .findPagedList();

        pagedList.loadCount(); // start counting in background Thread



        log.info("pagedList.getTotalCount:     {}", pagedList.getTotalCount());
        log.info("pagedList.getTotalPageCount: {}", pagedList.getTotalPageCount());
        log.info("pagedList.getPageIndex:      {}", pagedList.getPageIndex());
        log.info("pagedList.hasNext:           {}", pagedList.hasNext());
        log.info("pagedList.getPageSize:       {}", pagedList.getPageSize());// same as maxRows

        Customer.prettyPrint(pagedList.getList()); // print first page
    }




    /*@Test
    public void externalFilters() {
        System.out.println("simple Queries");

        ExpressionList<Address> asuncion = new QAddress()
                .city.icontains("asuncion")
                .getExpressionList();

        List<Customer> list = new QCustomer()
                .billingAddress.filterMany(asuncion)
                .findList();

        Customer.prettyPrint(list);
    }*/
}
