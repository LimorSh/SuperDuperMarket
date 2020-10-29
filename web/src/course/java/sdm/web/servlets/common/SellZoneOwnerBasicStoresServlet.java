package course.java.sdm.web.servlets.common;

import com.google.gson.Gson;
import course.java.sdm.engine.dto.BasicStoreDto;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.constants.Constants;
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

//@WebServlet(name = "SellZoneOwnerBasicStoresServlet", urlPatterns = {"/ownerBasicStores"})
public class SellZoneOwnerBasicStoresServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        String zoneNameFromSession = SessionUtils.getZoneName(request);
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        String usernameFromSession = SessionUtils.getUsername(request);

        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Collection<BasicStoreDto> OwnerBasicStores;
            synchronized (getServletContext()) {
                OwnerBasicStores = businessLogic.getOwnerBasicStoresDto(
                        zoneNameFromSession, usernameFromSession);
            }
            if (OwnerBasicStores.isEmpty()) {
                out.write(Constants.EMPTY_JSON_RESPONSE);
            }
            else {
                Collection<BasicStoreDto> basicStoresSortedById = OwnerBasicStores.stream().sorted
                        (Comparator.comparing(BasicStoreDto::getId))
                        .collect(Collectors.toList());
                String json = gson.toJson(basicStoresSortedById);
//                System.out.println(json);
                out.println(json);
            }
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
