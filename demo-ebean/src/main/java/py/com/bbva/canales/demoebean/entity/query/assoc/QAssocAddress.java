package py.com.bbva.canales.demoebean.entity.query.assoc;

import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Address;
import py.com.bbva.canales.demoebean.entity.query.QAddress;

/**
 * Association query bean for AssocAddress.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocAddress<R> extends TQAssocBean<Address,R> {

  public PLong<R> id;
  public PString<R> address;
  public PString<R> city;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QAddress>... properties) {
    return fetchProperties(properties);
  }

  public QAssocAddress(String name, R root) {
    super(name, root);
  }
}
