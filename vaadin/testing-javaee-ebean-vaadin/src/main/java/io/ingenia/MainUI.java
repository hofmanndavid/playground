package io.ingenia;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import io.ingenia.domain.Usuario;
import io.ingenia.ui.*;

import io.ingenia.util.UserHolder;
import org.vaadin.teemusa.sidemenu.SideMenu;

import java.lang.reflect.Field;

@Theme("valo")
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new LoginView(this::loggedIn));
    }

    CssLayout mil = null;
    SideMenu sideMenu = null;
    Navigator navigator = null;
    private void loggedIn(Usuario usuario) {
        UserHolder.setUser(usuario);

        // Usamos el addon que configura el menu lateral y contenido principal
        sideMenu = new SideMenu();
        setContent(sideMenu);

        // instanciamos el navegador para bach buton support y bookmarking
        navigator = new Navigator(this, sideMenu);
        setNavigator(navigator);

        sideMenu.setMenuCaption("Vtech demo");

        // Mostramos nombre de usuario en barra lateral
        sideMenu.setUserName(usuario.getFullName().split(" ")[0]);
        sideMenu.setUserIcon(FontAwesome.MALE);

        // Configuramos menu de usuario: Configuracion y Logout
        sideMenu.addUserMenuItem("Settings", FontAwesome.WRENCH, () -> // 229754
                Notification.show("Mostrando Configuraciones", Notification.Type.TRAY_NOTIFICATION));
        sideMenu.addUserMenuItem("Salir", FontAwesome.COG, () -> {
            VaadinSession.getCurrent().close();
            Page.getCurrent().reload();
        });

        navigator.setErrorView(new NotFoundView());

        // Configuramos las vistas

        addDivision("Administracion");
        addView("Usuarios", FontAwesome.USER, UsuariosView.NAME, UsuariosView.class);
        addView("Ciudades", FontAwesome.USER, CiudadView.NAME, CiudadView.class);
        addView("Personas", FontAwesome.USER, PersonaView.NAME, PersonaView.class);

        mil.addComponent(new Label("&nbsp; ", ContentMode.HTML));
        addView("Acerca de", FontAwesome.ADN, AcercadeView.NAME, AcercadeView.class);


//        navigator.navigateTo(AcercadeView.NAME);

    }

    private void addDivision(String name) {
        try {
            Field fmil = sideMenu.getClass().getDeclaredField("menuItemsLayout");
            fmil.setAccessible(true);
            mil = (CssLayout) fmil.get(sideMenu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mil.addComponent(new Label("&nbsp; &nbsp;"+FontAwesome.BARS.getHtml()+"&nbsp; "+name, ContentMode.HTML));
    }

    private void addView(String menuName, Resource icon, String viewname, Class<? extends View> viewClass) {
        sideMenu.addNavigation(menuName, icon, viewname);
        navigator.addView(viewname, viewClass);
    }
}
