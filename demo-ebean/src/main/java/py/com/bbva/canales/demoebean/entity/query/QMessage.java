package py.com.bbva.canales.demoebean.entity.query;

import io.ebean.EbeanServer;
import io.ebean.typequery.PBoolean;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.PUtilDate;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Message;
import py.com.bbva.canales.demoebean.entity.query.assoc.QAssocInteraction;

/**
 * Query bean for Message.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QMessage extends TQRootBean<Message,QMessage> {

  private static final QMessage _alias = new QMessage(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QMessage alias() {
    return _alias;
  }

  public PLong<QMessage> id;
  public PUtilDate<QMessage> fecha;
  public PBoolean<QMessage> sentByClient;
  public PString<QMessage> message;
  public PString<QMessage> aditional;
  public PString<QMessage> opName;
  public QAssocInteraction<QMessage> interaction;


  /**
   * Construct with a given EbeanServer.
   */
  public QMessage(EbeanServer server) {
    super(Message.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QMessage() {
    super(Message.class);
  }

  /**
   * Construct for Alias.
   */
  private QMessage(boolean dummy) {
    super(dummy);
  }
}
