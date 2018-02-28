package demo.ejb;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.FutureRowCount;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.annotation.Transactional;
import demo.entity.Usuario;
import demo.entity.query.QUsuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
@Path("/login")
@TransactionManagement(TransactionManagementType.BEAN)
public class UserLogic {

    private static final AtomicLong al = new AtomicLong(0);
    @GET()
    @Produces("text/html")
    public String login(String username, String password) {

        return Ebean.execute(() -> {

            Usuario u = new Usuario();
            u.setEmail("new@email.here");
            u.setUsername("new Username"+ al.getAndIncrement());
            u.setFullName("David Hofmann");
            u.setPassword("new pas");
            Ebean.save(u);

            List<Usuario> list = new QUsuario()
                    .id.gt(0L)
                    .findList();

            QUsuario fu = new QUsuario();
            fu = fu.id.gt(5L);
            FutureRowCount<Usuario> fcount = fu.findFutureCount();
            PagedList<Usuario> pagedList = fu.findPagedList();


            return list.size()+" found";
        });

    }
}
