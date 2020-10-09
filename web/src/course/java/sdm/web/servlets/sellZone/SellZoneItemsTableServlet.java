package course.java.sdm.web.servlets.sellZone;

import com.google.gson.Gson;
import course.java.sdm.engine.dto.ZoneDetailsDto;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@WebServlet(name = "SellZoneItemsTableServlet", urlPatterns = {"/itemsTable"})
public class SellZoneItemsTableServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("text/html;charset=UTF-8");

        String zoneNameFromSession = SessionUtils.getZoneName(request);
        System.out.println(zoneNameFromSession);

        response.getWriter().print("success after click link: " + zoneNameFromSession);


//        response.setContentType("application/json");
//
//        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
//
//        try (PrintWriter out = response.getWriter()) {
//            Gson gson = new Gson();
//            Set<ZoneDetailsDto> zones = businessLogic.getZonesDetailsDto();
//            List<ZoneDetailsDto> zonesSortedByName = zones.stream().sorted
//                    (Comparator.comparing(ZoneDetailsDto::getZoneName))
//                    .collect(Collectors.toList());
//            String json = gson.toJson(zonesSortedByName);
//            System.out.println(json);
//            out.println(json);
//            out.flush();
//        }
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
