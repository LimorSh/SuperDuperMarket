package course.java.sdm.web.servlets.addOrder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import course.java.sdm.engine.dto.DiscountDto;
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

//@WebServlet(name = "GetDiscountsServlet", urlPatterns = {"/setDiscounts"})
public class GetDiscountsServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        String zoneNameFromSession = SessionUtils.getZoneName(request);

        String itemsAndQuantitiesFromParameter = request.getParameter(Constants.ITEMS_AND_QUANTITIES_PARAM_KEY);
        JsonObject itemsAndQuantitiesJson = new JsonParser().parse(itemsAndQuantitiesFromParameter).getAsJsonObject();
        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();
        itemsAndQuantitiesJson.entrySet().forEach( entry ->
        {
            int itemId = Integer.parseInt(entry.getKey());
            float quantity = entry.getValue().getAsFloat();
            itemsIdsAndQuantities.put(itemId, quantity);
        });

        Collection<DiscountDto> discounts;
        String orderCategoryFromParameter = request.getParameter(Constants.CHOSEN_ORDER_CATEGORY_PARAM_KEY);
        if (orderCategoryFromParameter.equals(Constants.STATIC_ORDER_CATEGORY_STR)) {
            int storeId = Integer.parseInt(request.getParameter(Constants.CHOSEN_STORE_ID_PARAM_KEY));
            synchronized (this) {
                discounts = businessLogic.getRelevantDiscounts(zoneNameFromSession, storeId, itemsIdsAndQuantities);
            }
        }
        else {
            synchronized (this) {
                discounts = businessLogic.getRelevantDiscounts(zoneNameFromSession, itemsIdsAndQuantities);
            }
        }

        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Collection<DiscountDto> discountsSortedByName
                    = discounts.stream().sorted
                    (Comparator.comparing(DiscountDto::getName))
                    .collect(Collectors.toList());
            String json = gson.toJson(discountsSortedByName);
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
