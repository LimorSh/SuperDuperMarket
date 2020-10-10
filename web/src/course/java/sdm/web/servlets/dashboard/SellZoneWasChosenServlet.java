package course.java.sdm.web.servlets.dashboard;

import course.java.sdm.web.constants.Constants;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "ChargeCreditServlet", urlPatterns = {"/pages/dashboard/sellZoneChosen"})
public class SellZoneWasChosenServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String zoneName = request.getParameter(Constants.ZONE_NAME);
        request.getSession(false).setAttribute(Constants.ZONE_NAME, zoneName);
        response.getWriter().print("success - zone was chosen");
        System.out.println("success - zone was chosen");
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
