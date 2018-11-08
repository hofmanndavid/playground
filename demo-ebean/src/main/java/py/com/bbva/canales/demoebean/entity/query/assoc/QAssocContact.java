package py.com.bbva.canales.demoebean.entity.query.assoc;

import io.ebean.typequery.PEnum;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Contact;
import py.com.bbva.canales.demoebean.entity.Contact.Type;
import py.com.bbva.canales.demoebean.entity.query.QContact;

/**
 * Association query bean for AssocContact.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocContact<R> extends TQAssocBean<Contact,R> {

  public PLong<R> id;
  public PString<R> contact;
  public PEnum<R,Type> type;
  public QAssocCustomer<R> customer;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QContact>... properties) {
    return fetchProperties(properties);
  }

  public QAssocContact(String name, R root) {
    super(name, root);
  }
}
