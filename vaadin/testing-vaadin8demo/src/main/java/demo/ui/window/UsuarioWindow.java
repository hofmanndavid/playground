package demo.ui.window;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NotEmptyValidator;
import com.vaadin.data.validator.NotNullValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import demo.ejb.PersistenceBean;
import demo.entity.Usuario;
import demo.util.ResourceLocator;

import java.util.function.Consumer;

import static net.hdavid.easylayout.L.*;

public class UsuarioWindow extends Window {

    TextField userFullnameTextField = new TextField("Nombre");
    TextField emailTextField = new TextField("Correo");
    TextField usernameTextField = new TextField("Usuario");
    PasswordField passwordField = new PasswordField("Contraseña");
    Button guardarBtn = new Button("Guardar");
    Button cerrar = new Button("Cerrar");

    PersistenceBean pb = ResourceLocator.locate(PersistenceBean.class);

    private final Usuario usuario;
    final Consumer<Usuario> whenDone;
    public UsuarioWindow(final Usuario usuario, Consumer<Usuario> whenDone) {
        this.usuario = usuario;
        this.whenDone = whenDone;

        cerrar.addClickListener(cl -> {
            UsuarioWindow.this.close();
            whenDone.accept(null); // salimos sin guardar nada
        });
        cerrar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE); // ejecutar click listener del boton cerrar al presionar ESCAPE

        // layout del formulario
        FormLayout form = new FormLayout(userFullnameTextField, emailTextField, usernameTextField, passwordField);
        form.setSizeUndefined();
        form.setMargin(false);
        form.setCaption("Formulario de Usuario");

        // Layouting
        setContent(
                ve(_MARGIN,
                        form,
                        ho(cerrar, guardarBtn, ValoTheme.BUTTON_PRIMARY), Alignment.BOTTOM_RIGHT)
        );
        // Configuracion de ventana
        setCaption("Usuario");
        center();
        setSizeUndefined();
        setModal(true);

        configurarBinding();
    }

    private void configurarBinding() {
        Binder<Usuario> binder = new Binder<>();

        // definir instancia de Field a usar
        binder.forField(userFullnameTextField)
                // validacion con predicado
                .withValidator(
                        fullname -> fullname.length() >= 5,
                        "Nombre muy corto")
                // La configuracion finaliza con bind()
                .bind(
                        // callback para obtener el valor del bean
                        Usuario::getFullName,
                        // callback para setear el valor del bean
                        Usuario::setFullName
                );

        // shorthand
        binder.bind(usernameTextField, Usuario::getUsername, Usuario::setUsername);

        // with validation
        // definir instancia de Field a usar
        binder.forField(emailTextField)
                .withValidator(new EmailValidator("No es una direccion de correo valida"))
                .bind( Usuario::getEmail, Usuario::setEmail );

        binder.forField(passwordField)
                .withValidator(new NotEmptyValidator<>("Contraseña no puede estar vacia"))
                .withValidator(new NotNullValidator("Contraseña no puede estar vacia"))
                .bind( Usuario::getPassword, Usuario::setPassword);

        binder.load(usuario);

        guardarBtn.addClickListener(cl -> {
            try {
                if (binder.validate().isOk()) {
                    binder.save(usuario);
                    Usuario ug = null;//pb.genericSave(usuario);
                    whenDone.accept(ug);
                    close();
                }
            } catch (ValidationException e) {
                e.printStackTrace();
                Notification.show("Error guardando: "+e.getMessage());
            }
        });
    }


}
