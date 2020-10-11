package course.java.sdm.web.servlets.dashboard;

import course.java.sdm.web.constants.Constants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "SetChosenSellZoneServlet", urlPatterns = {"/pages/dashboard/sellZoneChosen"})
public class SetChosenSellZoneServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String zoneName = request.getParameter(Constants.ZONE_NAME);
        request.getSession(false).setAttribute(Constants.ZONE_NAME, zoneName);
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
