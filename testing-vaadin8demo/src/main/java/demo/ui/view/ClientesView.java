package demo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import demo.ejb.PersistenceBean;
import demo.entity.Cliente;
import demo.ui.window.ClienteWindow;
import demo.util.ResourceLocator;

import static net.hdavid.easylayout.L.*;

public class ClientesView extends VerticalLayout implements View {
    public static final String NAME = "Clientes";
    Grid<Cliente> clientesGrid = new Grid<>();

    Button nuevoBtn = new Button("Nuevo");
    Button modificarBtn = new Button("Modificar");
    Button eliminarBtn = new Button("Eliminar");

    PersistenceBean qb = ResourceLocator.locate(PersistenceBean.class);

    public ClientesView() {

        clientesGrid.addColumn("Id", c -> c.getId()+"");
        clientesGrid.addColumn("Nombre", Cliente::getNombre);
        clientesGrid.addColumn("Apellido", Cliente::getApellido);

        nuevoBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new ClienteWindow(new Cliente(), u -> this.loadClientes()))
        );

        modificarBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new ClienteWindow(clientesGrid.getSelectedItem().get(),
                                u -> this.loadClientes()))
        );

        eliminarBtn.addClickListener(cl -> {
//            qb.remove(clientesGrid.getSelectedItem());
            clientesGrid.setSelectedItem(null);
            loadClientes();
        });

        clientesGrid.addSelectionListener(e -> configureABMButtons() );
        configureABMButtons();

        HorizontalLayout top = ho(_FULL_WIDTH, Alignment.BOTTOM_RIGHT,
                _EXPANDER,
                eliminarBtn, ValoTheme.BUTTON_DANGER,
                modificarBtn,
                nuevoBtn, ValoTheme.BUTTON_PRIMARY);
        ve(this, _FULL_SIZE, _MARGIN, top, clientesGrid, _FULL_SIZE, _EXPAND);
    }

    void configureABMButtons(){
        boolean itemSelected = clientesGrid.getSelectedItem().isPresent();
        modificarBtn.setEnabled(itemSelected);
        eliminarBtn.setEnabled(itemSelected);

    }
    private void loadClientes() {
//        clientesGrid.setItems(
//                qb.genericQuery(em ->
//                        em.createQuery("select e from Cliente e").getResultList()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadClientes();
    }
}
