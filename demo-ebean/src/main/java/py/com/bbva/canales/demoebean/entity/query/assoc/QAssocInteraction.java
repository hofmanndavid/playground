package py.com.bbva.canales.demoebean.entity.query.assoc;

import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.PUtilDate;
import io.ebean.typequery.TQAssocBean;
import io.ebean.typequery.TQProperty;
import io.ebean.typequery.TypeQueryBean;
import py.com.bbva.canales.demoebean.entity.Interaction;
import py.com.bbva.canales.demoebean.entity.query.QInteraction;

/**
 * Association query bean for AssocInteraction.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@TypeQueryBean
public class QAssocInteraction<R> extends TQAssocBean<Interaction,R> {

  public PLong<R> id;
  public PString<R> opName;
  public PString<R> customerName;
  public PString<R> customerDocumentNumber;
  public PUtilDate<R> fecha;
  public PString<R> idCanal;

  /**
   * Eagerly fetch this association loading the specified properties.
   */
  @SafeVarargs
  public final R fetch(TQProperty<QInteraction>... properties) {
    return fetchProperties(properties);
  }

  public QAssocInteraction(String name, R root) {
    super(name, root);
  }
}
