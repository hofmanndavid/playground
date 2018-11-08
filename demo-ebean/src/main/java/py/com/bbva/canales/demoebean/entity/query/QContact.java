package py.com.bbva.canales.demoebean.entity.query;

import io.ebean.EbeanServer;
import io.ebean.typequery.PEnum;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Contact;
import py.com.bbva.canales.demoebean.entity.Contact.Type;
import py.com.bbva.canales.demoebean.entity.query.assoc.QAssocCustomer;

/**
 * Query bean for Contact.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QContact extends TQRootBean<Contact,QContact> {

  private static final QContact _alias = new QContact(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QContact alias() {
    return _alias;
  }

  public PLong<QContact> id;
  public PString<QContact> contact;
  public PEnum<QContact,Type> type;
  public QAssocCustomer<QContact> customer;


  /**
   * Construct with a given EbeanServer.
   */
  public QContact(EbeanServer server) {
    super(Contact.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QContact() {
    super(Contact.class);
  }

  /**
   * Construct for Alias.
   */
  private QContact(boolean dummy) {
    super(dummy);
  }
}
