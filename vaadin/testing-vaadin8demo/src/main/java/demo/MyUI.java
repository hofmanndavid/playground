package demo;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import demo.ui.view.*;
import org.vaadin.teemusa.sidemenu.SideMenu;
import org.vaadin.teemusa.sidemenu.SideMenuUI;
import demo.entity.*;
import demo.ui.*;
import demo.util.*;

@Theme("valo")
//@Push
@SideMenuUI
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setContent(new LoginView(this::loggedIn));
//        loggedIn(ResourcemonitorLocator.locate(UserLogic.class).login("admin", "admin").get());
    }

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

        sideMenu.setMenuCaption("Demo JUGPy");

        // Mostramos nombre de usuario en barra lateral
        sideMenu.setUserName(usuario.getFullName().split(" ")[0]);
        sideMenu.setUserIcon(FontAwesome.MALE);

        // Configuramos menu de usuario: Configuracion y Logout
        sideMenu.addUserMenuItem("Settings", FontAwesome.WRENCH, () -> // 229754
                Notification.show("Mostrando Configuraciones", Notification.Type.TRAY_NOTIFICATION) );
        sideMenu.addUserMenuItem("Salir", FontAwesome.COG, () -> {
            VaadinSession.getCurrent().close();
            Page.getCurrent().reload();
        });

        // Configuramos las vistas
        addView("Acerca de",  FontAwesome.ADN,    AcercadeView.NAME, AcercadeView.class);
        addView("Usuarios",   FontAwesome.USER, UsuariosView.NAME, UsuariosView.class);
        addView("Categorias", FontAwesome.CIRCLE,    CategoriasView.NAME, CategoriasView.class);
        addView("Clientes",   FontAwesome.DASHBOARD,    ClientesView.NAME, ClientesView.class);

    }

    private void addView(String menuName, Resource icon, String viewname, Class<? extends View> viewClass) {
        sideMenu.addNavigation(menuName, icon, viewname);
        navigator.addView(viewname, viewClass);
    }

}
