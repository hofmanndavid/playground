package io.hdavid;

import io.undertow.Undertow;
import io.undertow.jsp.HackInstanceManager;
import io.undertow.jsp.JspServletBuilder;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.servlet.util.DefaultClassIntrospector;
import org.apache.jasper.deploy.*;
import org.apache.tomcat.InstanceManager;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String [] args) throws ServletException, IOException {
    final PathHandler servletPath = new PathHandler();
    final ServletContainer container = ServletContainer.Factory.newInstance();

    DeploymentInfo builder = new DeploymentInfo()
            .setClassLoader(Main.class.getClassLoader())
            .setContextPath("/servletContext")
//            .setClassIntrospecter(DefaultClassIntrospector.INSTANCE)
            .setDeploymentName("servletContext.war")
            .setResourceManager(new ClassPathResourceManager(App.class.getClassLoader(), ""))

            .addServlet(JspServletBuilder.createServlet("Default Jsp Servlet", "*.jsp"));

//      Map<String, TagLibraryInfo> tags = toJSTL();
      HashMap<String, TagLibraryInfo> tags =
              TldLocator.createTldInfos();
      JspServletBuilder.setupDeployment(builder, new HashMap<String, JspPropertyGroup>(), tags, new HackInstanceManager());

    DeploymentManager manager = container.addDeployment(builder);
    manager.deploy();
    servletPath.addPrefixPath(builder.getContextPath(), manager.start());

    Undertow server = Undertow.builder()
              .addHttpListener(8080, "localhost")
              .setHandler(servletPath)//path)
              .setIoThreads(3)
              .setWorkerThreads(3)
              .build();
      server.start();
  }

    public static Map<String, TagLibraryInfo> toJSTL() {
        Map<String, TagLibraryInfo> tags = new HashMap<>();

        String[][] jstlInfos = {
//                {"http://java.sun.com/jstl/core_rt",        "/META-INF/c-1_0-rt.tld",     "1.0", "1.0"},
//                {"http://java.sun.com/jstl/core",           "/META-INF/c.tld",            "1.0", "1.1"},
//                {"http://java.sun.com/jsp/jstl/functions",  "/META-INF/fn.tld",           "1.0", "1.1"},
//                {"http://java.sun.com/jstl/fmt_rt",         "/META-INF/fmt-1_0-rt.tld",   "1.0", "1.0"},
//                {"http://java.sun.com/jstl/fmt",            "/META-INF/fmt.tld",          "1.0", "1.1"},
//                {"http://java.sun.com/jsp/jstl/sql",        "/META-INF/sql.tld",          "1.0", "1.1"},
//                {"http://java.sun.com/jstl/sql_rt",         "/META-INF/sql-1_0-rt.tld",   "1.0", "1.1"},
//                {"http://java.sun.com/jsp/jstl/xml",        "/META-INF/x.tld",            "1.0", "1.1"},
//                {"http://java.sun.com/jstl/xml_rt",         "/META-INF/x-1_0-rt.tld",     "1.0", "1.1"}
                {"http://java.sun.com/jsp/jstl/core",                           "/META-INF/c.tld",                   "2.1", "1.1"},
                {"http://java.sun.com/jsp/jstl/fmt",                            "/META-INF/fmt.tld",                 "2.0", "1.1"},
                {"http://java.sun.com/jsp/jstl/functions",                      "/META-INF/fn.tld",                  "2.0", "1.1"},
                {"http://java.sun.com/jsp/jstl/sql",                            "/META-INF/sql.tld",                 "2.0", "1.1"},
                {"http://java.sun.com/jsp/jstl/xml",                            "/META-INF/x.tld",                   "2.0", "1.1"},
                {"http://java.sun.com/jstl/core_rt",                            "/META-INF/c-1_0-rt.tld",            "1.2", "1.0"},
                {"http://java.sun.com/jstl/core",                               "/META-INF/c-1_0.tld",               "1.2", "1.0"},
                {"http://java.sun.com/jstl/fmt_rt",                             "/META-INF/fmt-1_0-rt.tld",          "1.2", "1.0"},
                {"http://java.sun.com/jstl/fmt",                                "/META-INF/fmt-1_0.tld",             "1.2", "1.0"},
                {"http://java.sun.com/jstl/sql_rt",                             "/META-INF/sql-1_0-rt.tld",          "1.2", "1.0"},
                {"http://java.sun.com/jstl/sql",                                "/META-INF/sql-1_0.tld",             "1.2", "1.0"},
                {"http://java.sun.com/jstl/xml_rt",                             "/META-INF/x-1_0-rt.tld",            "1.2", "1.0"},
                {"http://java.sun.com/jstl/xml",                                "/META-INF/x-1_0.tld",               "1.2", "1.0"},
                {"http://jakarta.apache.org/taglibs/standard/permittedTaglibs", "/META-INF/permittedTaglibs.tld",    "2.0", "1.1"},
                {"http://jakarta.apache.org/taglibs/standard/scriptfree",       "/META-INF/scriptfree.tld",          "2.0", "1.1"},
        };

        for (String[] jstlinfo : jstlInfos) {
            TagLibraryInfo info = new TagLibraryInfo();
            info.setUri(jstlinfo[0]);
            info.setPath(jstlinfo[1]);
            info.setVersion(jstlinfo[2]);
            info.setTlibversion(jstlinfo[3]);
            tags.put("/"+jstlinfo[1], info);
        }

        return tags;
    }

}
