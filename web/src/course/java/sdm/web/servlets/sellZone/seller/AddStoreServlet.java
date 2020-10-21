package course.java.sdm.web.servlets.sellZone.seller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//@WebServlet(name = "AddStoreServlet", urlPatterns = {"/pages/sellZone/seller/addStore/createNewStore"})
public class AddStoreServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

//        String zoneNameFromSession = SessionUtils.getZoneName(request);
//        String usernameFromSession = SessionUtils.getUsername(request);
//        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
//        AccountManager accountManager = ServletUtils.getAccountManager(getServletContext());
//
//        String dateFromParameter = request.getParameter(Constants.DATE_PARAM_KEY);
//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateFromParameter);
//        String locationXFromParameter = request.getParameter(Constants.LOCATION_X_PARAM_KEY);
//        String locationYFromParameter = request.getParameter(Constants.LOCATION_Y_PARAM_KEY);
//        int locationX = Integer.parseInt(locationXFromParameter);
//        int locationY = Integer.parseInt(locationYFromParameter);
//        String itemsAndQuantitiesFromParameter = request.getParameter(Constants.ITEMS_AND_QUANTITIES_PARAM_KEY);
//        JsonObject itemsAndQuantitiesJson = new JsonParser().parse(itemsAndQuantitiesFromParameter).getAsJsonObject();
//        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();
//        itemsAndQuantitiesJson.entrySet().forEach( entry -> {
//            int itemId = Integer.parseInt(entry.getKey());
//            float quantity = entry.getValue().getAsFloat();
//            itemsIdsAndQuantities.put(itemId, quantity);
//        });
//
//        String appliedOffersFromParameter =
//                request.getParameter(Constants.APPLIED_OFFERS_PARAM_KEY);
//        JsonObject appliedOffersJson = new JsonParser().parse(appliedOffersFromParameter).getAsJsonObject();
//        Map<String, Collection<Integer>> appliedOffers = new HashMap<>();
//        appliedOffersJson.entrySet().forEach( appliedOffersEntry -> {
//            Collection<Integer> offersStoreItemsIds = new ArrayList<>();
//            String discountName = appliedOffersEntry.getKey();
//            String offersStoreItemsIdsStr = appliedOffersEntry.getValue().getAsString();
//            String[] offersStoreItemsIdsStrArr = offersStoreItemsIdsStr.split(" ");
//            for (String storeItemIdStr : offersStoreItemsIdsStrArr) {
//                int storeItemId = Integer.parseInt(storeItemIdStr);
//                offersStoreItemsIds.add(storeItemId);
//            }
//            appliedOffers.put(discountName, offersStoreItemsIds);
//        });
//
//        int orderId;
//        String orderCategoryFromParameter = request.getParameter(Constants.CHOSEN_ORDER_CATEGORY_PARAM_KEY);
//        if (orderCategoryFromParameter.equals(Constants.STATIC_ORDER_CATEGORY_STR)) {
//            String storeIdFromParameter = request.getParameter(Constants.CHOSEN_STORE_ID_PARAM_KEY);
//            int storeId = Integer.parseInt(storeIdFromParameter);
//            synchronized (this) {
//                orderId = businessLogic.createOrder(accountManager, zoneNameFromSession, usernameFromSession, date, locationX, locationY,
//                        storeId, itemsIdsAndQuantities, appliedOffers);
//            }
//        }
//        else {
//            synchronized (this) {
//                orderId = businessLogic.createOrder(accountManager, zoneNameFromSession, usernameFromSession, date, locationX, locationY,
//                        itemsIdsAndQuantities, appliedOffers);
//            }
//        }
        response.getWriter().print("success");
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
