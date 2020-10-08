package course.java.sdm.web.servlets;

import com.google.gson.Gson;
import course.java.sdm.engine.dto.ZoneDetailsDto;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.engine.engine.users.User;
import course.java.sdm.engine.engine.users.UserManager;
import course.java.sdm.web.utils.ServletUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

//@WebServlet(name = "UsersListServlet", urlPatterns = {"/sellZonesTable"})
public class SellZonesTableServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());

        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            ZoneDetailsDto zoneDetailsDto = businessLogic.getZoneDetailsDto();
            Set<ZoneDetailsDto> zones = new HashSet<>();
            zones.add(zoneDetailsDto);
            String json = gson.toJson(zones);
            System.out.println(json);
            out.println(json);
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
}
