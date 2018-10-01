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
import io.ingenia.domain.Ciudad;
import io.ingenia.domain.Usuario;
import io.ingenia.domain.query.QCiudad;
import io.ingenia.domain.query.QUsuario;
import io.ingenia.util.ResourceLocator;

import java.util.function.Consumer;

import static net.hdavid.easylayout.L.*;

public class CiudadView extends VerticalLayout implements View {
    public static final String NAME = "Ciudades";

    Grid<Ciudad> grid = new Grid<>();
    Button nuevoBtn = new Button("Nuevo");
    Button modificarBtn = new Button("Modificar");
    Button eliminarBtn = new Button("Eliminar");

    public CiudadView() {

        grid.addColumn(u->u.getId()+"").setCaption("Id");
        grid.addColumn(Ciudad::getNombre).setCaption("Nombre");

        nuevoBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new FormWindow(new Ciudad(), u -> this.loadGrid()))
        );

        modificarBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new FormWindow(grid.getSelectionModel().getSelectedItems().iterator().next(),
                                          u -> this.loadGrid()))
        );

        eliminarBtn.addClickListener(cl -> {
//            qb.remove(grid.getSelectedItem());
            grid.getSelectionModel().deselectAll();
            loadGrid();
        });

        grid.addSelectionListener(e -> configureABMButtons() );
        configureABMButtons();

        HorizontalLayout top = ho(_FULL_WIDTH,
                _EXPANDER,
                eliminarBtn, ValoTheme.BUTTON_DANGER,
                modificarBtn,
                nuevoBtn, ValoTheme.BUTTON_PRIMARY);
        ve(this, _FULL_SIZE, top, grid, _FULL_SIZE, _EXPAND);
    }

    void configureABMButtons(){
        boolean itemSelected = !grid.getSelectionModel().getSelectedItems().isEmpty();
        modificarBtn.setEnabled(itemSelected);
        eliminarBtn.setEnabled(itemSelected);

    }
    private void loadGrid() {
        grid.setItems( new QCiudad().findList() );
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadGrid();
    }

    public static class FormWindow extends Window {

        TextField nombre = new TextField("Nombre");

        Button guardarBtn = new Button("Guardar");
        Button cerrar = new Button("Cerrar");

        PersistenceBean pb = ResourceLocator.locate(PersistenceBean.class);

        private final Ciudad entity;
        final Consumer<Ciudad> whenDone;
        public FormWindow(final Ciudad entity, Consumer<Ciudad> whenDone) {
            this.entity = entity;
            this.whenDone = whenDone;

            cerrar.addClickListener(cl -> {
                FormWindow.this.close();
                whenDone.accept(null); // salimos sin guardar nada
            });
            cerrar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE); // ejecutar click listener del boton cerrar al presionar ESCAPE

            // layout del formulario
            FormLayout form = new FormLayout(nombre);
            form.setSizeUndefined();
            form.setMargin(false);
            form.setCaption("Formulario de Ciudad");

            // Layouting
            setContent( ve(_MARGIN, form,
                    ho(cerrar, guardarBtn, ValoTheme.BUTTON_PRIMARY), Alignment.BOTTOM_RIGHT)
            );
            // Configuracion de ventana
            setCaption("Ciudad");
            center();
            setSizeUndefined();
            setModal(true);

            configurarBinding();
        }

        private void configurarBinding() {
            Binder<Ciudad> binder = new Binder<>();

            binder.bind(nombre, Ciudad::getNombre,Ciudad::setNombre);

            binder.setBean(entity);

            guardarBtn.addClickListener(cl -> {
                try {
                    if (binder.validate().isOk()) {
                        binder.writeBean(entity);

                        pb.exf(es -> es.save(entity) );
                        whenDone.accept(entity);
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
