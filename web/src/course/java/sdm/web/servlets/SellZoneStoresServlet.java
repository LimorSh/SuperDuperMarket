package course.java.sdm.web.servlets;

import com.google.gson.Gson;
import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

//@WebServlet(name = "SellZoneStoresServlet", urlPatterns = {"/stores"})
public class SellZoneStoresServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        String zoneNameFromSession = SessionUtils.getZoneName(request);
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());

        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Collection<StoreDto> stores = businessLogic.getStoresDto(zoneNameFromSession);
            Collection<StoreDto> storesSortedById = stores.stream().sorted
                    (Comparator.comparing(StoreDto::getId))
                    .collect(Collectors.toList());
            String json = gson.toJson(storesSortedById);
            System.out.println(json);
            out.println(json);
            out.flush();
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
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
