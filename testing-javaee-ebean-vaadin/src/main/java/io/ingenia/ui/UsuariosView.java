package io.ingenia.ui;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import io.ingenia.crosscut.PersistenceBean;
import io.ingenia.domain.Usuario;
import io.ingenia.domain.query.QUsuario;
import io.ingenia.util.ResourceLocator;

import java.util.function.Consumer;

import static net.hdavid.easylayout.L.*;

public class UsuariosView extends VerticalLayout implements View {
    public static final String NAME = "Usuarios";

    Grid<Usuario> usuariosGrid = new Grid<>();
    Button nuevoBtn = new Button("Nuevo");
    Button modificarBtn = new Button("Modificar");
    Button eliminarBtn = new Button("Eliminar");

    public UsuariosView() {

        usuariosGrid.addColumn(u->u.getId()+"").setCaption("Id");
        usuariosGrid.addColumn(Usuario::getFullName).setCaption("Nombre");
        usuariosGrid.addColumn(Usuario::getUsername).setCaption("Usuario");

        nuevoBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new UsuarioWindow(new Usuario(), u -> this.loadUsers()))
        );

        modificarBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new UsuarioWindow(usuariosGrid.getSelectionModel().getSelectedItems().iterator().next(),
                                          u -> this.loadUsers()))
        );

        eliminarBtn.addClickListener(cl -> {
//            qb.remove(grid.getSelectedItem());
            usuariosGrid.getSelectionModel().deselectAll();
            loadUsers();
        });

        usuariosGrid.addSelectionListener( e -> configureABMButtons() );
        configureABMButtons();

        HorizontalLayout top = ho(_FULL_WIDTH,
                _EXPANDER,
                eliminarBtn, ValoTheme.BUTTON_DANGER,
                modificarBtn,
                nuevoBtn, ValoTheme.BUTTON_PRIMARY);
        ve(this, _FULL_SIZE, top, usuariosGrid, _FULL_SIZE, _EXPAND);
    }

    void configureABMButtons(){
        boolean itemSelected = !usuariosGrid.getSelectionModel().getSelectedItems().isEmpty();
        modificarBtn.setEnabled(itemSelected);
        eliminarBtn.setEnabled(itemSelected);

    }
    private void loadUsers() {
        usuariosGrid.setItems( new QUsuario().findList() );
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadUsers();
    }

    public static class UsuarioWindow extends Window {

        TextField userFullnameTextField = new TextField("Nombre");
        TextField emailTextField = new TextField("Correo");
        TextField usernameTextField = new TextField("Usuario");
        PasswordField passwordField = new PasswordField("Contraseña");
        Button guardarBtn = new Button("Guardar");
        Button cerrar = new Button("Cerrar");
        CheckBox isAdmin = new CheckBox("Administrador");
        CheckBox isImpresor = new CheckBox("Impresor");
        CheckBox isCOnsulta = new CheckBox("Consulta");

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
            FormLayout form = new FormLayout(userFullnameTextField, emailTextField, usernameTextField, passwordField,
                    ho(isAdmin, isImpresor, isCOnsulta));
            form.setSizeUndefined();
            form.setMargin(false);
            form.setCaption("Formulario de Usuario");

            // Layouting
            setContent( ve(_MARGIN, form,
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
            binder.bind(isAdmin, Usuario::isRoleAdmin, Usuario::setRoleAdmin);
            binder.bind(isImpresor, Usuario::isRoleImpresor, Usuario::setRoleImpresor);
            binder.bind(isCOnsulta, Usuario::isRoleConsulta, Usuario::setRoleConsulta);


            // with validation
            // definir instancia de Field a usar
            binder.forField(emailTextField)
                    .withValidator(new EmailValidator("No es una direccion de correo valida"))
                    .bind( Usuario::getEmail, Usuario::setEmail );

            binder.forField(passwordField)
                    .withValidator(pf -> pf != null && !pf.isEmpty(), "Contraseña no puede estar vacia")
                    .bind( Usuario::getPassword, Usuario::setPassword);

            binder.setBean(usuario);

            guardarBtn.addClickListener(cl -> {
                try {
                    if (binder.validate().isOk()) {
                        binder.writeBean(usuario);

                        pb.exf(es -> es.save(usuario) );
                        whenDone.accept(usuario);
                        close();
                    }
                } catch (ValidationException e) {
                    e.printStackTrace();
                    Notification.show("Error guardando: "+e.getMessage());
                }
            });
        }


    }
}
