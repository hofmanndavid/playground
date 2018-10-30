package py.com.bbva.canales.demoebean.entity.query;

import io.ebean.EbeanServer;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.PUtilDate;
import io.ebean.typequery.TQRootBean;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Interaction;

/**
 * Query bean for Interaction.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QInteraction extends TQRootBean<Interaction,QInteraction> {

  private static final QInteraction _alias = new QInteraction(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QInteraction alias() {
    return _alias;
  }

  public PLong<QInteraction> id;
  public PString<QInteraction> opName;
  public PString<QInteraction> customerName;
  public PString<QInteraction> customerDocumentNumber;
  public PUtilDate<QInteraction> fecha;
  public PString<QInteraction> idCanal;


  /**
   * Construct with a given EbeanServer.
   */
  public QInteraction(EbeanServer server) {
    super(Interaction.class, server);
  }

  /**
   * Construct using the default EbeanServer.
   */
  public QInteraction() {
    super(Interaction.class);
  }

  /**
   * Construct for Alias.
   */
  private QInteraction(boolean dummy) {
    super(dummy);
  }
}
