package io.hdavid.test.entity;

import io.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Log
public abstract class BasicEbeanEntity extends Model {

    @Id
    @Getter @Setter private Long id;

    public BasicEbeanEntity() {

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"#"+getId();
    }

    @Override
    public int hashCode() {
        if (id == null)
            throw new IllegalStateException(this.getClass().getSimpleName()+".hashCode() used before having an id");
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;

        if (this.getId() == null)
            throw new IllegalStateException(this.getClass().getSimpleName()+"-this.equals() used before having an id");

//        log.info("sefl: "+this.getClass().getSimpleName() + " comparing to "+o.getClass().getSimpleName());

        // ebean's enhancement will NOT make names different as hibernate does
        if (!o.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
            return false;

        BasicEbeanEntity beeo = (BasicEbeanEntity) o;

        if (beeo.getId() == null)
            throw new IllegalStateException(o.getClass().getSimpleName()+"-other.equals() used before having an id");

        return ((BasicEbeanEntity)o).getId().equals(id);

    }
}