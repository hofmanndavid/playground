package py.com.bbva.canales.demoebean.entity.query;

import io.ebean.EbeanServer;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Address;

/**
 * Query bean for Address.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAddress extends TQRootBean<Address,QAddress> {

  private static final QAddress _alias = new QAddress(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QAddress alias() {
    return _alias;
  }

  public PLong<QAddress> id;
  public PString<QAddress> address;
  public PString<QAddress> city;


  /**
   * Construct with a given EbeanServer.
   */
  public QAddress(EbeanServer server) {
    super(Address.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QAddress() {
    super(Address.class);
  }

  /**
   * Construct for Alias.
   */
  private QAddress(boolean dummy) {
    super(dummy);
  }
}
