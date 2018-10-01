package demo.ui.window;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import demo.ejb.PersistenceBean;
import demo.entity.Categoria;
import demo.util.ResourceLocator;

import java.util.function.Consumer;

import static net.hdavid.easylayout.L.*;

public class CategoriaWindow extends Window {

    TextField codigo = new TextField("Codigo");
    TextField descripcion = new TextField("Descripcion");

    Button guardarBtn = new Button("Guardar");
    Button cerrar = new Button("Cerrar");

    PersistenceBean pb = ResourceLocator.locate(PersistenceBean.class);

    private final Categoria categoria;
    final Consumer<Categoria> whenDone;
    public CategoriaWindow(final Categoria categoria, Consumer<Categoria> whenDone) {
        this.categoria = categoria;
        this.whenDone = whenDone;

        cerrar.addClickListener(cl -> {
            CategoriaWindow.this.close();
            whenDone.accept(null); // salimos sin guardar nada
        });
        cerrar.setClickShortcut(ShortcutAction.KeyCode.ESCAPE); // ejecutar click listener del boton cerrar al presionar ESCAPE

        // layout del formulario
        FormLayout form = new FormLayout(codigo, descripcion);
        form.setSizeUndefined();
        form.setMargin(false);
        form.setCaption("Formulario de Categoria");

        // Layouting
        setContent(
                ve(_MARGIN,
                        form,
                        ho(cerrar, guardarBtn, ValoTheme.BUTTON_PRIMARY), Alignment.BOTTOM_RIGHT)
        );

        // Configuracion de ventana
        setCaption("Categoria");
        center();
        setSizeUndefined();
        setModal(true);

        configurarBinding();
    }

    private void configurarBinding() {
        Binder<Categoria> binder = new Binder<>();
        binder.bind(codigo, Categoria::getCodigo, Categoria::setCodigo);
        binder.bind(descripcion, Categoria::getDescripcion, Categoria::setDescripcion);

        binder.load(categoria);

        guardarBtn.addClickListener(cl -> {
            try {
                if (binder.validate().isOk()) {
                    binder.save(categoria);
                    Categoria cg = null;//pb.genericSave(categoria);
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
