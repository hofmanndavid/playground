package io.ingenia.crosscut;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import io.ingenia.MainUI;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
public class VServlet extends VaadinServlet {

}