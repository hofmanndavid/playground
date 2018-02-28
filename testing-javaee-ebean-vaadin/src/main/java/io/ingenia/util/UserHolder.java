package io.ingenia.util;

import com.vaadin.server.VaadinSession;
import io.ingenia.domain.Usuario;

public class UserHolder {
    public static Usuario get() {
        return (Usuario) VaadinSession.getCurrent()
                .getAttribute(Usuario.class.getName());
    }
    public static void setUser(Usuario user) {
        VaadinSession.getCurrent()
                .setAttribute(Usuario.class.getName(), user);
    }
}
