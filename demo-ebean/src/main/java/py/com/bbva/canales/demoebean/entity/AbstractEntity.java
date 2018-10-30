package py.com.bbva.canales.demoebean.entity;

import io.ebean.Model;
import io.ebean.annotation.SoftDelete;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity extends Model implements Serializable {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

//	@Version
//    @Getter
//	private int version;

//    @SoftDelete
//    @Getter
//	private boolean deleted;



    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        if (id == null)
            throw new IllegalStateException(this.getClass().getSimpleName()+".hashCode() used in Collection before having an id");
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;

        if (this.getId() == null)
            throw new IllegalStateException(this.getClass().getSimpleName()+".equals() used in Collection before having an id");

        // ebean's enhancement doesn't make class names different at runtime as hibernate does
        if (!o.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
            return false;

        AbstractEntity beeo = (AbstractEntity) o;

        if (beeo.getId() == null)
            throw new IllegalStateException(o.getClass().getSimpleName()+".equals() used in Collection before having an id");

        return beeo.getId().equals(id);

    }

}