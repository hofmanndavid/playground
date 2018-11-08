package py.com.bbva.canales.demoebean.entity.query.assoc;

import io.ebean.typequery.PEnum;
import io.ebean.typequery.PLocalDate;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Customer;
import py.com.bbva.canales.demoebean.entity.Customer.Status;
import py.com.bbva.canales.demoebean.entity.query.QCustomer;

/**
 * Association query bean for AssocCustomer.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocCustomer<R> extends TQAssocBean<Customer,R> {

  public PLong<R> id;
  public PString<R> name;
  public PString<R> docNumber;
  public PEnum<R,Status> status;
  public PLocalDate<R> registered;
  public QAssocAddress<R> billingAddress;
  public QAssocContact<R> contacts;
  public QAssocMessage<R> messages;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QCustomer>... properties) {
    return fetchProperties(properties);
  }

  public QAssocCustomer(String name, R root) {
    super(name, root);
  }
}
