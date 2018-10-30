package py.com.bbva.canales.demoebean.entity.query;

import io.ebean.EbeanServer;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.User;

/**
 * Query bean for User.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QUser extends TQRootBean<User,QUser> {

  private static final QUser _alias = new QUser(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QUser alias() {
    return _alias;
  }

  public PLong<QUser> id;
  public PString<QUser> email;
  public PString<QUser> password;
  public PString<QUser> name;
  public PString<QUser> roles;


  /**
   * Construct with a given EbeanServer.
   */
  public QUser(EbeanServer server) {
    super(User.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QUser() {
    super(User.class);
  }

  /**
   * Construct for Alias.
   */
  private QUser(boolean dummy) {
    super(dummy);
  }
}
