package demo.ui;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import demo.ejb.UserLogic;
import demo.entity.Usuario;
import demo.util.ResourceLocator;

import java.util.Optional;
import java.util.function.Consumer;

import static net.hdavid.easylayout.L.*;

public class LoginView extends VerticalLayout {

    Label loginInfoText = new Label("Sistema de Prueba JUGPy VTECH #8");
    Panel loginPanel = new Panel("Ingreso al sistema");
    TextField username = new TextField("Usuario", "");
    PasswordField password = new PasswordField("Contraseña");
    Button login = new Button("Ingresar");

    UserLogic userLogic = ResourceLocator.locate(UserLogic.class);

    final Consumer<Usuario> loginListener;
    public LoginView(Consumer<Usuario> loginListener) {
        this.loginListener = loginListener;
        buildUI();
    }

    private void buildUI() {

        FormLayout loginForm = new FormLayout(username, password);
        loginForm.setMargin(false);
        loginForm.setSizeUndefined();

        loginPanel.setContent(
                ve(_NOOP, _MARGIN,
                        loginForm,
                        login, Alignment.BOTTOM_RIGHT, ValoTheme.BUTTON_PRIMARY));
        loginPanel.setSizeUndefined();

        ve(this, _FULL_SIZE,
                _EXPANDER,
                loginInfoText, Alignment.MIDDLE_CENTER, ValoTheme.LABEL_COLORED, ValoTheme.LABEL_BOLD, ValoTheme.LABEL_H1,
                loginPanel, Alignment.MIDDLE_CENTER,
                _EXPANDER);

        login.addClickListener(e -> tryLogin());
        username.focus();
    }


    private void tryLogin() {
        Optional<Usuario> user = null;//userLogic.login(username.getValue(), password.getValue());
        if (user.isPresent()) {
            loginListener.accept(user.get());
        } else {
            Notification.show("Error de ingreso de session",
                    "Favor verifique sus credenciales e intente de nuevo.",
                    Notification.Type.HUMANIZED_MESSAGE);
            username.focus();
            username.selectAll();
        }
    }

}
