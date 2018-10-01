package demo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import demo.ejb.PersistenceBean;
import demo.entity.Categoria;
import demo.ui.window.CategoriaWindow;
import demo.util.ResourceLocator;

import static net.hdavid.easylayout.L.*;

public class CategoriasView extends VerticalLayout implements View {

    public static final String NAME = "Categorias";

    Grid<Categoria> categoriasGrid = new Grid<>();
    Button nuevoBtn = new Button("Nuevo");
    Button modificarBtn = new Button("Modificar");
    Button eliminarBtn = new Button("Eliminar");

    PersistenceBean qb = ResourceLocator.locate(PersistenceBean.class);

    public CategoriasView() {

        categoriasGrid.addColumn("Id", c -> c.getId()+"");
        categoriasGrid.addColumn("Codigo", Categoria::getCodigo);
        categoriasGrid.addColumn("Descripcion", Categoria::getDescripcion);

        nuevoBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new CategoriaWindow(new Categoria(), u -> this.loadCategorias()))
        );

        modificarBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new CategoriaWindow(categoriasGrid.getSelectedItem().get(),
                                u -> this.loadCategorias()))
        );

        eliminarBtn.addClickListener(cl -> {
//            qb.remove(categoriasGrid.getSelectedItem());
            categoriasGrid.setSelectedItem(null);
            loadCategorias();
        });

        categoriasGrid.addSelectionListener(e -> configureABMButtons() );
        configureABMButtons();

        HorizontalLayout top = ho(_FULL_WIDTH,
                _EXPANDER,
                eliminarBtn, ValoTheme.BUTTON_DANGER,
                modificarBtn,
                nuevoBtn, ValoTheme.BUTTON_PRIMARY);
        ve(this, _FULL_SIZE, _MARGIN, top, categoriasGrid, _FULL_SIZE, _EXPAND);
    }

    void configureABMButtons(){
        boolean itemSelected = categoriasGrid.getSelectedItem().isPresent();
        modificarBtn.setEnabled(itemSelected);
        eliminarBtn.setEnabled(itemSelected);

    }
    private void loadCategorias() {
//        categoriasGrid.setItems(
//                qb.genericQuery(em ->
//                        em.createQuery("select e from Categoria e").getResultList()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadCategorias();
    }
}
