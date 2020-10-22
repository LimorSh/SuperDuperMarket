package course.java.sdm.web.servlets.sellZone.seller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "AddItemServlet", urlPatterns = {"/pages/sellZone/seller/addItem/createNewItem"})
public class AddItemServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        String zoneNameFromSession = SessionUtils.getZoneName(request);
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());

        String itemNameFromParameter = request.getParameter(Constants.ITEM_NAME_PARAM_KEY);
        String chosenPurchaseCategoryParameter = request.getParameter(Constants.CHOSEN_PURCHASE_CATEGORY_PARAM_KEY);
        String storesIdsAndPricesFromParameter = request.getParameter(Constants.STORES_IDS_AND_PRICES_PARAM_KEY);
        JsonObject storeIdsAndPricesJson = new JsonParser().parse(storesIdsAndPricesFromParameter).getAsJsonObject();
        Map<Integer, Float> storeIdsAndPrices = new HashMap<>();
        storeIdsAndPricesJson.entrySet().forEach( entry -> {
            int storeId = Integer.parseInt(entry.getKey());
            float price = entry.getValue().getAsFloat();
            storeIdsAndPrices.put(storeId, price);
        });

        synchronized (this) {
            try {
                businessLogic.createNewItem(zoneNameFromSession, itemNameFromParameter,
                        chosenPurchaseCategoryParameter, storeIdsAndPrices);
                response.getWriter().print("");
            }
            catch (Exception e) {
                response.getWriter().print(e.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
