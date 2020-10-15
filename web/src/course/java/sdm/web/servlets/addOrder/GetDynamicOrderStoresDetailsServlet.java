package course.java.sdm.web.servlets.addOrder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import course.java.sdm.engine.dto.DynamicOrderStoreDetailsDto;
import course.java.sdm.engine.dto.ItemDto;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//@WebServlet(name = "GetDynamicOrderStoresDetailsServlet", urlPatterns = {"/setDynamicOrderStoresDetails"})
public class GetDynamicOrderStoresDetailsServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        String zoneNameFromSession = SessionUtils.getZoneName(request);

        String locationXFromParameter = request.getParameter(Constants.LOCATION_X_PARAM_KEY);
        String locationYFromParameter = request.getParameter(Constants.LOCATION_Y_PARAM_KEY);
        int locationX = Integer.parseInt(locationXFromParameter);
        int locationY = Integer.parseInt(locationYFromParameter);
        String itemsAndQuantitiesFromParameter = request.getParameter(Constants.ITEMS_AND_QUANTITIES_PARAM_KEY);
        JsonObject itemsAndQuantitiesJson = new JsonParser().parse(itemsAndQuantitiesFromParameter).getAsJsonObject();
        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();
        itemsAndQuantitiesJson.entrySet().forEach( entry ->
        {
            int itemId = Integer.parseInt(entry.getKey());
            float quantity = entry.getValue().getAsFloat();
            itemsIdsAndQuantities.put(itemId, quantity);
        });

        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Collection<DynamicOrderStoreDetailsDto> storesDetails =
                    businessLogic.getDynamicOrderStoresDetailsDto(zoneNameFromSession,
                            itemsIdsAndQuantities, locationX, locationY);
            Collection<DynamicOrderStoreDetailsDto> storesDetailsSortedById
                    = storesDetails.stream().sorted
                    (Comparator.comparing(DynamicOrderStoreDetailsDto::getId))
                    .collect(Collectors.toList());
            String json = gson.toJson(storesDetailsSortedById);
            System.out.println(json);
            out.println(json);
            out.flush();
        }
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
