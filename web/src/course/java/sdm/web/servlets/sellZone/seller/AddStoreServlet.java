package course.java.sdm.web.servlets.sellZone.seller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.engine.engine.notifications.NotificationManager;
import course.java.sdm.web.constants.Constants;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

//@WebServlet(name = "AddStoreServlet", urlPatterns = {"/pages/sellZone/seller/addStore/createNewStore"})
public class AddStoreServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        String zoneNameFromSession = SessionUtils.getZoneName(request);
        String usernameFromSession = SessionUtils.getUsername(request);
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        NotificationManager notificationManager = ServletUtils.getNotificationManager(getServletContext());

        String storeNameFromParameter = request.getParameter(Constants.STORE_NAME_PARAM_KEY);
        String locationXFromParameter = request.getParameter(Constants.LOCATION_X_PARAM_KEY);
        String locationYFromParameter = request.getParameter(Constants.LOCATION_Y_PARAM_KEY);
        int locationX = Integer.parseInt(locationXFromParameter);
        int locationY = Integer.parseInt(locationYFromParameter);
        String ppkFromParameter = request.getParameter(Constants.PPK_PARAM_KEY);
        int ppk = Integer.parseInt(ppkFromParameter);

        String itemIdsAndPricesFromParameter = request.getParameter(Constants.ITEM_IDS_AND_PRICES_PARAM_KEY);
        JsonObject itemIdsAndPricesJson = new JsonParser().parse(itemIdsAndPricesFromParameter).getAsJsonObject();
        Map<Integer, Float> itemIdsAndPrices = new HashMap<>();
        itemIdsAndPricesJson.entrySet().forEach( entry -> {
            int itemId = Integer.parseInt(entry.getKey());
            float price = entry.getValue().getAsFloat();
            itemIdsAndPrices.put(itemId, price);
        });

        synchronized (getServletContext()) {
            try {
                businessLogic.createNewStore(notificationManager, zoneNameFromSession, usernameFromSession, storeNameFromParameter,
                        locationX, locationY, ppk, itemIdsAndPrices);
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
