package demo.ejb;

import demo.entity.Usuario;

//import javax.ejb.Singleton;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


//@Singleton
//@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
public class EntityAutoCreation {

//	@PersistenceContext
//	private EntityManager em;

	public void atStartup() {
//        List<Usuario> users = (List) em.createQuery("select u from Usuario u where u.username = 'admin'").getResultList();
//        if (users.isEmpty()) {
//            Usuario user = new Usuario();
//            user.setFullName("David Hofmann");
//            user.setUsername("admin");
//            user.setPassword("admin");
//            em.persist(user);
//        }

    }

}
