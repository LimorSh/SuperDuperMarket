package course.java.sdm.web.servlets.sellZone;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "RedirectOrderHistoryServlet", urlPatterns = {"/pages/sellZone/orderHistory"})
public class RedirectOrderHistoryServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        when sending redirect, tomcat has a shitty logic how to calculate the URL given, weather its relative or not
        you can read about it here:
        https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServletResponse.html#sendRedirect(java.lang.String)
        the best way (IMO) is to fetch the context path dynamically and build the redirection from it and on
         */
        response.sendRedirect(request.getContextPath() + "/pages/sellZone/orderHistory/order-history.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
