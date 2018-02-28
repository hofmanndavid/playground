package io.ingenia.crosscut;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ServletListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("contextInitialized called");
    }

    public void contextDestroyed(ServletContextEvent event) {

    }
}