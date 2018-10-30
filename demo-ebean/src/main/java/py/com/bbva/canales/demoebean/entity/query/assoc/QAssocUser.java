package py.com.bbva.canales.demoebean.entity.query.assoc;

import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.User;
import py.com.bbva.canales.demoebean.entity.query.QUser;

/**
 * Association query bean for AssocUser.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocUser<R> extends TQAssocBean<User,R> {

  public PLong<R> id;
  public PString<R> email;
  public PString<R> password;
  public PString<R> name;
  public PString<R> roles;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QUser>... properties) {
    return fetchProperties(properties);
  }

  public QAssocUser(String name, R root) {
    super(name, root);
  }
}
