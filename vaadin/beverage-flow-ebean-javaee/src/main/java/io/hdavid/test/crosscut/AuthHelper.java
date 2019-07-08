package io.hdavid.test.crosscut;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import io.hdavid.test.entity.User;

public final class AuthHelper {

    public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = AuthHelper.class.getCanonicalName();

    private AuthHelper() {
    }

    public static User getCurrentUser() {
        User currentUser = (User) VaadinService.getCurrentRequest()
                .getWrappedSession()
                .getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        if (currentUser == null) {
            VaadinService.getCurrentRequest()
                    .getWrappedSession()
                    .removeAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        } else {
            VaadinService.getCurrentRequest()
                    .getWrappedSession()
                    .setAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY, currentUser);
        }
    }

    public static boolean isUserSignedIn() {
        return getCurrentUser() != null;
    }

    public static void signOut() {
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().getPage().reload();
    }

}
