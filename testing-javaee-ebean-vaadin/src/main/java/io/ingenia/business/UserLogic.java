package io.ingenia.business;

import io.ingenia.domain.Usuario;
import io.ingenia.domain.query.QUsuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.Optional;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserLogic {

    public Optional<Usuario> login(String username, String password) {

        Usuario u = new QUsuario()
                .username.eq(username)
                .password.eq(password)
                .findUnique(); // only one call, no need for tx

        return Optional.ofNullable(u);
    }
}
