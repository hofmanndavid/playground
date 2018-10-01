package demo.ejb.servlet;

import com.avaje.ebean.EbeanServerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ServletListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("contextInitialized called");
    }

    public void contextDestroyed(ServletContextEvent event) {
//        EbeanServerFactory.shutdown();
    }
}