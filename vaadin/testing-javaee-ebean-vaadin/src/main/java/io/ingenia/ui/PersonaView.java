package io.ingenia.ui;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import io.ebean.Ebean;
import io.ingenia.crosscut.PersistenceBean;
import io.ingenia.domain.Ciudad;
import io.ingenia.domain.Direccion;
import io.ingenia.domain.Persona;
import io.ingenia.domain.Usuario;
import io.ingenia.domain.query.QCiudad;
import io.ingenia.domain.query.QDireccion;
import io.ingenia.domain.query.QPersona;
import io.ingenia.util.ResourceLocator;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

import static net.hdavid.easylayout.L.*;

public class PersonaView extends VerticalLayout implements View {
    public static final String NAME = "Personas";

    ComboBox<Ciudad> ciudades = new ComboBox<>("Ciudades", new QCiudad().findList());
    Grid<Persona> grid = new Grid<>();
    Grid<Direccion> dirGrid = new Grid<>();
    Button agregarDireccionBtn = new Button("Agregar Direccion");
    Button nuevoBtn = new Button("Nuevo");
    Button modificarBtn = new Button("Modificar");
    Button eliminarBtn = new Button("Eliminar");

    public PersonaView() {

        ciudades.setItemCaptionGenerator(c->c.getNombre());
        ciudades.addValueChangeListener(vcl->loadGrid());

        dirGrid.addColumn(u->u.getId()+"").setCaption("Id");
        dirGrid.addColumn(d->d.getCiudad().getNombre()).setCaption("Nombre Ciudad");
        dirGrid.addColumn(Direccion::getCalle).setCaption("Calle");
        dirGrid.addColumn(Direccion::getNro).setCaption("Nro");

        grid.addColumn(u->u.getId()+"").setCaption("Id");
        grid.addColumn(Persona::getNombre).setCaption("Nombre");
        grid.addColumn(Persona::getApellido).setCaption("Apellido");

        grid.addSelectionListener(sl->{
            loadDirGrid();
        });

        nuevoBtn.addClickListener(cl ->
                UI.getCurrent().addWindow(
                        new FormWindow(new Persona(), u -> this.loadGrid()))
        );


        agregarDireccionBtn.addClickListener(cl ->{
            Direccion dir = new Direccion();
            dir.setPersona(grid.getSelectionModel().getFirstSelectedItem().get());
            UI.getCurrent().addWindow(
                        new DireccionWindow(dir, u -> this.loadGrid()));
        });

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

        HorizontalLayout top = ho(Alignment.BOTTOM_RIGHT, _FULL_WIDTH,ciudades,
                _EXPANDER,
                eliminarBtn, ValoTheme.BUTTON_DANGER,
                modificarBtn,
                nuevoBtn, ValoTheme.BUTTON_PRIMARY);
        ve(this, _FULL_SIZE, top,
                ho(_FULL_SIZE, ve(_FULL_SIZE, grid, _FULL_SIZE, dirGrid, _FULL_SIZE ), _EXPAND, ve(agregarDireccionBtn)), _EXPAND);
    }

    void configureABMButtons(){
        boolean itemSelected = !grid.getSelectionModel().getSelectedItems().isEmpty();
        modificarBtn.setEnabled(itemSelected);
        eliminarBtn.setEnabled(itemSelected);

    }
    private void loadGrid() {
        QPersona qdir = new QPersona();

        if (ciudades.getValue() != null) {
            qdir.direcciones
                    .ciudad.id.eq(ciudades.getValue().getId());
        }

        grid.setItems( qdir.findList() );
        loadDirGrid();
    }

    private void loadDirGrid() {
        Optional<Persona> sp = grid.getSelectionModel().getFirstSelectedItem();

        if (sp.isPresent())
            dirGrid.setItems( new QDireccion().persona.id.eq(sp.get().getId()).findList() );
        else
            dirGrid.setItems( new ArrayList<>());

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loadGrid();
    }

    public static class FormWindow extends Window {

        TextField nombre = new TextField("Nombre");
        TextField apellido = new TextField("Apellido");
//        ComboBox<Ciudad> ciudades = new ComboBox<>("Ciudad", new QCiudad().findList());
        Button guardarBtn = new Button("Guardar");
        Button cerrar = new Button("Cerrar");

        PersistenceBean pb = ResourceLocator.locate(PersistenceBean.class);

        private final Persona entity;
        final Consumer<Persona> whenDone;
        public FormWindow(final Persona entity, Consumer<Persona> whenDone) {
            this.entity = entity;
            this.whenDone = whenDone;

            cerrar.addClickListener(cl -> {
                FormWindow.this.close();
                whenDone.accept(null); // salimos sin guardar nada
            });
            cerrar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE); // ejecutar click listener del boton cerrar al presionar ESCAPE

            // layout del formulario
            FormLayout form = new FormLayout(nombre, apellido);
            form.setSizeUndefined();
            form.setMargin(false);
            form.setCaption("Formulario de Persona");

            // Layouting
            setContent( ve(_MARGIN, form,
                    ho(cerrar, guardarBtn, ValoTheme.BUTTON_PRIMARY), Alignment.BOTTOM_RIGHT)
            );
            // Configuracion de ventana
            setCaption("Persona");
            center();
            setSizeUndefined();
            setModal(true);

            configurarBinding();
        }

        private void configurarBinding() {
            Binder<Persona> binder = new Binder<>();

            binder.bind(nombre, Persona::getNombre, Persona::setNombre);
            binder.bind(apellido, Persona::getApellido, Persona::setApellido);

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


    public static class DireccionWindow extends Window {

        TextField calle = new TextField("calle");
        TextField nro = new TextField("Nro.");
        ComboBox<Ciudad> ciudades = new ComboBox<>("Ciudade", new QCiudad().findList());
        Button guardarBtn = new Button("Guardar");
        Button cerrar = new Button("Cerrar");

        PersistenceBean pb = ResourceLocator.locate(PersistenceBean.class);

        private final Direccion entity;
        final Consumer<Direccion> whenDone;
        public DireccionWindow(final Direccion entity, Consumer<Direccion> whenDone) {
            this.entity = entity;
            this.whenDone = whenDone;

            ciudades.setItemCaptionGenerator(c->c.getNombre());

            cerrar.addClickListener(cl -> {
                PersonaView.DireccionWindow.this.close();
                whenDone.accept(null); // salimos sin guardar nada
            });
            cerrar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE); // ejecutar click listener del boton cerrar al presionar ESCAPE

            // layout del formulario
            FormLayout form = new FormLayout(ciudades, calle, nro);
            form.setSizeUndefined();
            form.setMargin(false);
            form.setCaption("Formulario de Direccion");

            // Layouting
            setContent( ve(_MARGIN, form,
                    ho(cerrar, guardarBtn, ValoTheme.BUTTON_PRIMARY), Alignment.BOTTOM_RIGHT)
            );
            // Configuracion de ventana
            setCaption("Direccion");
            center();
            setSizeUndefined();
            setModal(true);

            configurarBinding();
        }

        private void configurarBinding() {
            Binder<Direccion> binder = new Binder<>();

            binder.bind(ciudades, Direccion::getCiudad,Direccion::setCiudad);
            binder.bind(calle, Direccion::getCalle,Direccion::setCalle);
            binder.bind(nro, Direccion::getNro,Direccion::setNro);

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
