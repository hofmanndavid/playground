package demo.ejb;

import lombok.SneakyThrows;
import demo.entity.Usuario;

//import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Function;

//@Stateless
public class PersistenceBean {

//    @PersistenceContext
//    EntityManager em;
//
//    public <T> List<T>  genericQuery(Function<EntityManager, List<T>> code) {
//        return code.apply(em);
//    }
//
//    @SneakyThrows
//    public <T> T genericSave(T entity) {
//
//        Object id = entity.getClass().getMethod("getId").invoke(entity);
//        if (id == null) {
//            em.persist(entity);
//            return entity;
//        } else {
//            entity = em.merge(entity);
//            return entity;
//        }
//
//    }
//    public void remove(Object entity) {
//        em.remove(entity);
//    }
}
