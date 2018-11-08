package py.com.bbva.canales.demoebean.entity.query;

import io.ebean.EbeanServer;
import io.ebean.typequery.PEnum;
import io.ebean.typequery.PLocalDate;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Customer;
import py.com.bbva.canales.demoebean.entity.Customer.Status;
import py.com.bbva.canales.demoebean.entity.query.assoc.QAssocAddress;
import py.com.bbva.canales.demoebean.entity.query.assoc.QAssocContact;
import py.com.bbva.canales.demoebean.entity.query.assoc.QAssocMessage;

/**
 * Query bean for Customer.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QCustomer extends TQRootBean<Customer,QCustomer> {

  private static final QCustomer _alias = new QCustomer(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QCustomer alias() {
    return _alias;
  }

  public PLong<QCustomer> id;
  public PString<QCustomer> name;
  public PString<QCustomer> docNumber;
  public PEnum<QCustomer,Status> status;
  public PLocalDate<QCustomer> registered;
  public QAssocAddress<QCustomer> billingAddress;
  public QAssocContact<QCustomer> contacts;
  public QAssocMessage<QCustomer> messages;


  /**
   * Construct with a given EbeanServer.
   */
  public QCustomer(EbeanServer server) {
    super(Customer.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QCustomer() {
    super(Customer.class);
  }

  /**
   * Construct for Alias.
   */
  private QCustomer(boolean dummy) {
    super(dummy);
  }
}
