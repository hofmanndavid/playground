package demo.ui.window;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import demo.ejb.PersistenceBean;
import demo.entity.Categoria;
import demo.entity.Cliente;
import demo.util.LocalDateToDateConverter;
import demo.util.ResourceLocator;

import java.util.function.Consumer;

import static net.hdavid.easylayout.L.*;

public class ClienteWindow extends Window {

    TextField nombre = new TextField("Nombre");
    TextField apellido = new TextField("Apellido");
    TextField ruc = new TextField("Ruc");
    DateField fechaNacimiento = new DateField("Fecha Nacimiento");
    ComboBox<Categoria> categorias = new ComboBox<>("Categorias");
    Button guardarBtn = new Button("Guardar");
    Button cerrar = new Button("Cerrar");

    PersistenceBean pb = ResourceLocator.locate(PersistenceBean.class);

    private final Cliente cliente;
    final Consumer<Cliente> whenDone;
    public ClienteWindow(final Cliente cliente, Consumer<Cliente> whenDone) {
        this.cliente = cliente;
        this.whenDone = whenDone;

        cerrar.addClickListener(cl -> {
            ClienteWindow.this.close();
            whenDone.accept(null); // salimos sin guardar nada
        });
        cerrar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE); // ejecutar click listener del boton cerrar al presionar ESCAPE

//        categorias.setItems(pb.genericQuery(em -> em.createQuery("select o from Categoria o").getResultList()));
        categorias.setItemCaptionGenerator(c -> c.getDescripcion());

        fechaNacimiento.setResolution(Resolution.DAY);

        // layout del formulario
        FormLayout form = new FormLayout(nombre, apellido, ruc, fechaNacimiento, categorias);
        form.setSizeUndefined();
        form.setMargin(false);
        form.setCaption("Formulario de Cliente");

        // Layouting
        setContent(
                ve(_MARGIN,
                        form,
                        ho(cerrar, guardarBtn, ValoTheme.BUTTON_PRIMARY), Alignment.BOTTOM_RIGHT)
        );
        // Configuracion de ventana
        setCaption("Cliente");
        center();
        setSizeUndefined();
        setModal(true);

        configurarBinding();
    }

    private void configurarBinding() {
        Binder<Cliente> binder = new Binder<>();
        binder.bind(nombre, Cliente::getNombre, Cliente::setNombre);
        binder.bind(apellido, Cliente::getApellido, Cliente::setApellido);
        binder.bind(ruc, Cliente::getRuc, Cliente::setRuc);
        binder.forField(fechaNacimiento)
            .withConverter(new LocalDateToDateConverter("Error de Conversion de fecha"))
            .bind(Cliente::getFechaNacimiento, Cliente::setFechaNacimiento);

        binder.forField(categorias)
                .bind(Cliente::getCategoria, Cliente::setCategoria);

        binder.load(cliente);

        guardarBtn.addClickListener(cl -> {
            try {
                if (binder.validate().isOk()) {
                    binder.save(cliente);
                    Cliente cg = null;//pb.genericSave(cliente);
                    whenDone.accept(cg);
                    close();
                }
            } catch (ValidationException e) {
                e.printStackTrace();
                Notification.show("Error guardando: "+e.getMessage());
            }
        });
    }


}
