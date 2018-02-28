package demo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ReportesView extends VerticalLayout implements View {
    public static final String NAME = "Reportes";
    public ReportesView(String label) {
        addComponent(new Label(label));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
