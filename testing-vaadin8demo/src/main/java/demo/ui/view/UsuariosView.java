package demo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import demo.ejb.PersistenceBean;
import demo.entity.Usuario;
import demo.ui.window.UsuarioWindow;
import demo.util.ResourceLocator;

import static net.hdavid.easylayout.L.*;

public class UsuariosView extends VerticalLayout implements View {
    public static final String NAME = "Usuarios";

    Grid<Usuario> usuariosGrid = new Grid<>();
    Button nuevoBtn = new Button("Nuevo");
    Button modificarBtn = new Button("Modificar");
    Button eliminarBtn = new Button("Eliminar");


    PersistenceBean qb = ResourceLocator.locate(PersistenceBean.class);

    public UsuariosView() {

        usuariosGrid.addColumn("Id", u -> u.getId()+"");
        usuariosGrid.addColumn("Nombre", Usuario::getFullName);
        usuariosGrid.addColumn("Usuario", Usuario::getUsername);

        nuevoBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new UsuarioWindow(new Usuario(), u -> this.loadUsers()))
        );

        modificarBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new UsuarioWindow(usuariosGrid.getSelectedItem().get(),
                                          u -> this.loadUsers()))
        );

        eliminarBtn.addClickListener(cl -> {
//            qb.remove(usuariosGrid.getSelectedItem());
            usuariosGrid.setSelectedItem(null);
            loadUsers();
        });

        usuariosGrid.addSelectionListener( e -> configureABMButtons() );
        configureABMButtons();

        HorizontalLayout top = ho(_FULL_WIDTH,
                _EXPANDER,
                eliminarBtn, ValoTheme.BUTTON_DANGER,
                modificarBtn,
                nuevoBtn, ValoTheme.BUTTON_PRIMARY);
        ve(this, _FULL_SIZE, _MARGIN, top, usuariosGrid, _FULL_SIZE, _EXPAND);
    }

    void configureABMButtons(){
        boolean itemSelected = usuariosGrid.getSelectedItem().isPresent();
        modificarBtn.setEnabled(itemSelected);
        eliminarBtn.setEnabled(itemSelected);

    }
    private void loadUsers() {
//        usuariosGrid.setItems(
//                qb.genericQuery(em ->
//                        em.createQuery("select u from Usuario u").getResultList()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadUsers();
    }
}
