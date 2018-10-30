package py.com.bbva.canales.demoebean.entity.query.assoc;

import io.ebean.typequery.PBoolean;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.PUtilDate;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Message;
import py.com.bbva.canales.demoebean.entity.query.QMessage;

/**
 * Association query bean for AssocMessage.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocMessage<R> extends TQAssocBean<Message,R> {

  public PLong<R> id;
  public PUtilDate<R> fecha;
  public PBoolean<R> sentByClient;
  public PString<R> message;
  public PString<R> aditional;
  public PString<R> opName;
  public QAssocInteraction<R> interaction;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QMessage>... properties) {
    return fetchProperties(properties);
  }

  public QAssocMessage(String name, R root) {
    super(name, root);
  }
}
