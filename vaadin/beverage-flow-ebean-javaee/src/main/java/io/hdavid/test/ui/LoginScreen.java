package io.hdavid.test.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import io.hdavid.test.AdminView;
import io.hdavid.test.MainLayout;
import io.hdavid.test.crosscut.AccessControl;
import io.hdavid.test.crosscut.AuthHelper;
import io.hdavid.test.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

@Route("Login")
@PageTitle("Login")
@JsModule("frontend://styles/shared-styles.js")
public class LoginScreen extends FlexLayout {

    @EJB
    AccessControl accessControl;

    @PostConstruct
    private void buildUI() {
        setSizeFull();
        setClassName("login-screen");

        // login form, centered in the available part of the screen
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(this::login);
        loginForm.addForgotPasswordListener(e -> Notification.show("Hint: same as username"));

        // layout to center login form when there is sufficient screen space
        FlexLayout centeringLayout = new FlexLayout();
        centeringLayout.setSizeFull();
        centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        centeringLayout.setAlignItems(Alignment.CENTER);
        centeringLayout.add(loginForm);

        // information text about logging in
        Component loginInformation = buildLoginInformation();

        add(loginInformation);
        add(centeringLayout);
    }

    private Component buildLoginInformation() {
        VerticalLayout loginInformation = new VerticalLayout();
        loginInformation.setClassName("login-information");

        H1 loginInfoHeader = new H1("Login Information");
        Span loginInfoText = new Span(
                "Log in as \"admin\" to have full access. Log in with any " +
                        "other username to have read-only access. For all " +
                        "users, the password is same as the username.");
        loginInformation.add(loginInfoHeader);
        loginInformation.add(loginInfoText);

        return loginInformation;
    }

    private void login(LoginForm.LoginEvent event) {
        User user = accessControl.attemptLogin(event.getUsername(), event.getPassword());

        if (user == null) {
            event.getSource().setError(true);
            return;
        }

        AuthHelper.setCurrentUser(user);
        if (user.hasRol(User.Rol.ADMIN)) {
            RouteConfiguration.forSessionScope()
                    .setRoute(AdminView.VIEW_NAME, AdminView.class, MainLayout.class);
        }

        getUI().get().navigate("");
    }

}
