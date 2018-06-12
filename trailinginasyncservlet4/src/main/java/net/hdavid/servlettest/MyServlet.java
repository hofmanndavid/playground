package net.hdavid.servlettest;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@WebServlet(asyncSupported = true)
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync(req, resp);

        // here you pass this asyncContext to any thread worker who
        // will be querying the REQ data
        // then you can send a response like

        ((HttpServletResponse)asyncContext.getResponse()).setHeader("Transfer-Encoding","chunked");
        asyncContext.getResponse().getOutputStream().write(new byte[]{});// write your bytes here
        asyncContext.getResponse().getOutputStream().flush();
        ((HttpServletResponse)asyncContext.getResponse()).setTrailerFields(() ->{
            return new HashMap<>(); // add any trailing headers
        });

    }
}
