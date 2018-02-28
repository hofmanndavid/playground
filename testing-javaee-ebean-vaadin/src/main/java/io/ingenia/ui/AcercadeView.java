package io.ingenia.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import static net.hdavid.easylayout.L.ve;

public class AcercadeView extends VerticalLayout implements View {

    public static final String NAME = "";

    public AcercadeView() {
        ve(this, //_MARGIN,
                new Label("Acerca de Sistema de Impresion de etiquetas del VES"),
                ValoTheme.LABEL_BOLD, ValoTheme.LABEL_H1, ValoTheme.LABEL_HUGE, ValoTheme.LABEL_COLORED);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
